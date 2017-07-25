#version 400 core

in vec2 passTexCoords;
in vec3 normal;
in vec3 toLight;
in vec3 toCam;

out vec4 outColor;

uniform sampler2D tex;

uniform float usesLighting;
uniform float usesNormalMap;

uniform float ambientFactor;
uniform float diffuseFactor;
uniform float specularFactor;
uniform int shininess;

uniform vec3 lightColor;
uniform float lightIntensity;
uniform vec3 lightDir;

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
		vec3 specular = specularFactor * pow(max(dot(reflectedLight, toCam), 0.0), shininess) * lightShade;
		
		finalColor = (ambient + diffuse) * texColor + specular;
		outColor = vec4(finalColor, 1.0);
	}else{
		outColor = vec4(texColor, 1.0);
	}
}