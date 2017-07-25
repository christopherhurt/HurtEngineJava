#version 400 core

in vec3 pos;
in vec2 texCoords;
in vec3 norm;

out vec2 passTexCoords;
out vec3 normal;
out vec3 toCam;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 transform;

uniform vec3 camLoc;

void main(){
	vec3 worldPos = (transform * vec4(pos, 1.0)).xyz;
	gl_Position = projection * view * vec4(worldPos, 1.0);
	passTexCoords = texCoords;
	normal = mat3(transpose(inverse(transform))) * normalize(norm);
	toCam = normalize(camLoc - worldPos);
}