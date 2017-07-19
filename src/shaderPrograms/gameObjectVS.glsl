#version 400 core

in vec3 pos;
in vec2 texCoords;

out vec2 passTexCoords;

uniform mat4 projection;
uniform mat4 view;
uniform mat4 transform;

void main(){
	gl_Position = projection * view * transform * vec4(pos, 1);
	passTexCoords = texCoords;
}