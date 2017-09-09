#version 400 core

in vec2 currentCoords;
in vec2 nextCoords;
flat in bool hasNext;
flat in float passTransitionAmount;

out vec4 outColor;

uniform sampler2D atlas;

void main(){
	vec4 currentColor = texture(atlas, currentCoords);
	vec4 nextColor = vec4(0.0);
	if(hasNext){
		nextColor = texture(atlas, nextCoords);
	}
	outColor = currentColor * passTransitionAmount + nextColor * (1.0 - passTransitionAmount);
}