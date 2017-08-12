#version 400 core

in vec2 currentCoords;
in vec2 nextCoords;

out vec4 outColor;

uniform sampler2D atlas;
uniform float transitionAmount;

void main(){
	vec4 currentColor = texture(atlas, currentCoords);
	vec4 nextColor = texture(atlas, nextCoords);
	outColor = currentColor * transitionAmount + nextColor * (1.0 - transitionAmount);
}