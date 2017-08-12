#version 400 core

in vec3 pos;
in vec2 texCoords;
in vec3 norm;
in vec3 tang;
in mat4 instanceTransform;

out vec3 fragPos;
out vec2 passTexCoords;
out vec3 normal;
out mat3 tangentSpace;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 uniformTransform;
uniform float drawInstanced;

void main(){
	mat4 transform = mat4(0.0);
	if(drawInstanced > 0.5){
		transform = instanceTransform;
	}else{
		transform = uniformTransform;
	}
	
	vec3 worldPos = (transform * vec4(pos, 1.0)).xyz;
	gl_Position = projection * view * vec4(worldPos, 1.0);
	fragPos = worldPos;
	passTexCoords = texCoords;
	mat3 adjustedTransform = mat3(transpose(inverse(transform)));
	normal = normalize(adjustedTransform * norm);
	
	vec3 tangent = normalize(adjustedTransform * tang);
	vec3 bitangent = cross(normal, tangent);
	tangentSpace = mat3(
		tangent.x, bitangent.x, normal.x,
		tangent.y, bitangent.y, normal.y,
		tangent.z, bitangent.z, normal.z
	);
}