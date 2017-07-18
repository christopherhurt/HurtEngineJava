#version 400 core

in vec2 passTexCoords;

out vec4 outColor;

uniform sampler2D tex;

void main(){
	vec4 texColor = texture(tex, passTexCoords);
	outColor = texColor;
}