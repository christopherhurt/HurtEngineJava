#version 400 core

in vec3 pos;
in vec2 texCoords;
in mat4 transformView;
in float totalTextures;
in float currentTexture;
in float transitionAmount;

out vec2 currentCoords;
out vec2 nextCoords;
flat out bool hasNext;
flat out float passTransitionAmount;

void main(){
	gl_Position = transformView * vec4(pos, 1.0);
	passTransitionAmount = transitionAmount;
	
	int sideLength = ceil(sqrt(totalTextures));
	float uvDelta = 1.0 / sideLength;
	int nextTexture = currentTexture + 1;
	hasNext = currentTextures < totalTextures;
	
	currentCoords = texCoords * uvDelta;
	nextCoords = texCoords * uvDelta;
	currentCoords.x += mod(currentTexture, sideLength) * uvDelta;
	currentCoords.y += currentTexture / sideLength * uvDelta;
	nextCoords.x += mod(nextTexture, sideLength);
	nextCoords.y += nextTexture / sideLength * uvDelta;
}