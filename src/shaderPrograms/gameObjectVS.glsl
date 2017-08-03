#version 400 core

in vec3 pos;
in vec2 texCoords;
in vec3 norm;
in vec3 tang;

out vec3 fragPos;
out vec2 passTexCoords;
out vec3 normal;

flat out vec3 camPos;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 transform;

uniform vec3 camLoc;

void main(){
	vec3 worldPos = (transform * vec4(pos, 1.0)).xyz;
	gl_Position = projection * view * vec4(worldPos, 1.0);
	passTexCoords = texCoords;
	norm = normalize(mat3(transpose(inverse(transform))) * norm);
	
	vec3 tang = normalize(mat3(transpose(inverse(transform))) * tang);
	vec3 bitang = cross(tang, norm);
	tangentSpace = mat3(
		tang.x, bitang.x, norm.x,
		tang.y, bitang.y, norm.y,
		tang.z, bitang.z, norm.z
	);
	
	fragPos = tangentSpace * worldPos;
	camPos = tangentSpace * camLoc;
	// TODO: apply tangent space matrix to light positions here
}