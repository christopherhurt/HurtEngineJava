#version 400 core

const int ARRAY_SIZE = 10000;

in vec3 fragPos;
in vec2 passTexCoords;
in vec3 normal;

flat in camPos;

out vec4 outColor;

uniform Material material;
uniform DirectionalLight[ARRAY_SIZE] dLights;
uniform int numDLights;
uniform PointLight[ARRAY_SIZE] pLights;
uniform int numPLights;
uniform Spotlight[ARRAY_SIZE] sLights;
uniform int numSLights;

struct Material {
	float usesLighting;
	float usesLightMap;
	float usesNormalMap;
	float usesDepthMap;
	
	float ambientFactor;
	float diffuseFactor;
	float specularFactor;
	int shininess;
	
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
}

struct PointLight {
	Light light;
	vec3 position;
	vec2 attenuation;
}

struct Spotlight {
	Light light;
	vec3 position;
	vec3 direction;
	float cosineCutoff;
}

void main(){
	// Texture
	vec3 texColor = texture(tex, passTexCoords).xyz;
	
	// Lighting
	if(usesLighting > 0.5){
		vec3 finalColor = vec3(0.0);
		vec3 lightShade = lightIntensity * lightColor;
		vec3 toLight = normalize(-lightDir);
		
		// Ambient
		vec3 ambient = ambientFactor * lightShade;
		
		// Diffuse
		float lightImpact = max(dot(normal, toLight), 0.0);
		vec3 diffuse = diffuseFactor * lightImpact * lightShade;
		
		// Specular
		vec3 reflectedLight = reflect(-toLight, normal);
		vec3 specular = specularFactor * max(pow(dot(reflectedLight, toCam), shininess), 0.0) * lightShade;
		
		finalColor = (ambient + diffuse) * texColor + specular;
		outColor = vec4(finalColor, 1.0);
	}else{
		outColor = vec4(texColor, 1.0);
	}
}