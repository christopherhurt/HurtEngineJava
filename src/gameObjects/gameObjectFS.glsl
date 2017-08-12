#version 400 core

const int MAX_LIGHTS = 62; // TODO: change

in vec3 fragPos;
in vec2 passTexCoords;
in vec3 normal;
in mat3 tangentSpace;

out vec4 outColor;

struct Material {
	float usesLighting;
	float usesLightMap;
	float usesNormalMap;
	float usesDepthMap;
	
	float ambientFactor;
	float diffuseFactor;
	float specularFactor;
	int shininess;
	
	float displacementFactor;
	float wrapDisplacedTexture;
	
	sampler2D diffuseMap;
	sampler2D lightMap;
	sampler2D normalMap;
	sampler2D depthMap;
};

struct Light {
	vec3 color;
	float intensity;
	float isOn;
};

struct DirectionalLight {
	Light light;
	vec3 direction;
};

struct PointLight {
	Light light;
	vec3 position;
	vec2 attenuation;
};

struct Spotlight {
	Light light;
	vec3 position;
	vec3 direction;
	float cosineInnerCutoff;
	float cosineOuterCutoff;
};

struct LightValues {
	vec3 ambient;
	vec3 diffuse;
	vec3 specular;
};

uniform vec3 camLoc;
uniform Material material;
uniform DirectionalLight[MAX_LIGHTS] dLights;
uniform int numDLights;
uniform PointLight[MAX_LIGHTS] pLights;
uniform int numPLights;
uniform Spotlight[MAX_LIGHTS] sLights;
uniform int numSLights;

vec2 applyDisplacement(vec3 toCam){
	vec2 texCoords = vec2(0.0);
	
	if(material.usesDepthMap > 0.5){
		const float MIN_DISPLACEMENT_LAYERS = 8.0;
		const float MAX_DISPLACEMENT_LAYERS = 64.0;
		const vec3 TANGENT_SPACE_UP = vec3(0.0, 0.0, 1.0);
		
		float numLayers = mix(MIN_DISPLACEMENT_LAYERS, MAX_DISPLACEMENT_LAYERS, abs(dot(TANGENT_SPACE_UP, toCam)));
		float layerDepth = 1.0 / numLayers;
		float currentLayerDepth = 0.0;
		
		vec2 proj = toCam.xy / toCam.z * material.displacementFactor;
		vec2 deltaTexCoords = proj / numLayers;
		vec2 currentTexCoords = passTexCoords;
		float currentHeight = texture(material.depthMap, currentTexCoords).r;
		
		while(currentLayerDepth < currentHeight){
			currentTexCoords -= deltaTexCoords;
			currentHeight = texture(material.depthMap, currentTexCoords).r;
			currentLayerDepth += layerDepth;
		}
		
		vec2 prevTexCoords = currentTexCoords + deltaTexCoords;
		float afterDepth = currentHeight - currentLayerDepth;
		float beforeDepth = texture(material.depthMap, prevTexCoords).r - currentLayerDepth + layerDepth;
		
		float weight = afterDepth / (afterDepth - beforeDepth);
		texCoords = prevTexCoords * weight + currentTexCoords * (1.0 - weight);
		
		if(material.wrapDisplacedTexture < 0.5)
			if(texCoords.x < 0.0 || texCoords.x > 1.0 || texCoords.y < 0.0 || texCoords.y > 1.0)
				discard;
	}else{
		texCoords = passTexCoords;
	}
	
	return texCoords;
}

void main(){
	if(material.usesLighting > 0.5){
		vec3 toCam = normalize(tangentSpace * (camLoc - fragPos));
		vec2 texCoords = applyDisplacement(toCam);
		vec4 texColor = texture(material.diffuseMap, texCoords);
		if(texColor.a < 0.1) discard;
		
		float ambientFactor = material.ambientFactor;
		float diffuseFactor = 0.0;
		float specularFactor = 0.0;
		int shininess = material.shininess;
		if(material.usesLightMap > 0.5){
			vec4 lightMapValue = texture(material.lightMap, texCoords);
			specularFactor = lightMapValue.r;
			if(lightMapValue.g > 0.0){
				diffuseFactor = 1.0;
			}else{
				diffuseFactor = lightMapValue.b;
			}
		}else{
			diffuseFactor = material.diffuseFactor;
			specularFactor = material.specularFactor;
		}
		
		vec3 norm = vec3(0.0);
		if(material.usesNormalMap > 0.5){
			norm = normalize(2.0 * texture(material.normalMap, texCoords).xyz - 1.0);
		}else{
			norm = normalize(tangentSpace * normal);
		}
		
		LightValues dValues;
		for(int i = 0; i < numDLights; i++){
			DirectionalLight light = dLights[i];
			if(light.light.isOn > 0.5){
				vec3 lightColor = light.light.intensity * light.light.color;
				vec3 toLight = normalize(tangentSpace * -light.direction);
				float diff = max(dot(norm, toLight), 0.0);
				float spec = pow(max(dot(reflect(-toLight, norm), toCam), 0.0), shininess);
				dValues.ambient += ambientFactor * lightColor;
				dValues.diffuse += diffuseFactor * diff * lightColor;
				dValues.specular += specularFactor * spec * lightColor;
			}
		}
		
		LightValues pValues;
		for(int i = 0; i < numPLights; i++){
			PointLight light = pLights[i];
			if(light.light.isOn > 0.5){
				const float attenC = 1.0;
				vec3 lightColor = light.light.intensity * light.light.color;
				vec3 difference = light.position - fragPos;
				vec3 toLight = normalize(tangentSpace * difference);
				float diff = max(dot(norm, toLight), 0.0);
				float spec = pow(max(dot(reflect(-toLight, norm), toCam), 0.0), shininess);
				float dist = length(difference);
				float atten = 1.0 / (attenC + light.attenuation.x * dist + light.attenuation.y * dist * dist);
				pValues.ambient += ambientFactor * lightColor;
				pValues.diffuse += diffuseFactor * atten * diff * lightColor;
				pValues.specular += specularFactor * atten * spec * lightColor;
			}
		}
		
		LightValues sValues;
		for(int i = 0; i < numSLights; i++){
			Spotlight light = sLights[i];
			if(light.light.isOn > 0.5){
				vec3 lightColor = light.light.intensity * light.light.color;
				vec3 toLight = normalize(tangentSpace * (light.position - fragPos));
				float theta = dot(toLight, normalize(tangentSpace * -light.direction));
				sValues.ambient += ambientFactor * lightColor;
				if(theta > light.cosineOuterCutoff){
					float diff = max(dot(norm, toLight), 0.0);
					float spec = pow(max(dot(reflect(-toLight, norm), toCam), 0.0), shininess);
					float epsilon = light.cosineInnerCutoff - light.cosineOuterCutoff;
					float smoothFactor = clamp((theta - light.cosineOuterCutoff) / epsilon, 0.0, 1.0);
					sValues.diffuse += diffuseFactor * smoothFactor * diff * lightColor;
					sValues.specular += specularFactor * smoothFactor * spec * lightColor;
				}
			}
		}
		
		vec3 ambient = dValues.ambient + pValues.ambient + sValues.ambient;
		vec3 diffuse = dValues.diffuse + pValues.diffuse + sValues.diffuse;
		vec3 specular = dValues.specular + pValues.specular + sValues.specular;
		outColor = vec4((ambient + diffuse) * texColor.xyz + specular, 1.0);
	}else{
		vec4 texColor = texture(material.diffuseMap, passTexCoords);
		if(texColor.a < 0.1) discard;
		outColor = texColor;
	}
}