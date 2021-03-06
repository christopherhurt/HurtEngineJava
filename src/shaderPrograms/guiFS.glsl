#version 400 core

in vec2 passTexCoords;

out vec4 outColor;

uniform sampler2D tex;

void main(){
	outColor = texture(tex, passTexCoords);
}