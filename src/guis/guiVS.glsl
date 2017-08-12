#version 400 core

in vec3 pos;
in vec2 texCoords;
in mat4 transform;

out vec2 passTexCoords;

void main(){
	gl_Position = transform * vec4(pos, 1.0);
	passTexCoords = texCoords;
}