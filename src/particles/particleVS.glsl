#version 400 core

in vec3 pos;
in vec2 texCoords;
in mat4 modelViewMatrix;

out vec2 currentCoords;
out vec2 nextCoords;

uniform int totalTextures;
uniform int currentTexture;

void main(){
	gl_Position = modelViewMatrix * vec4(pos, 1.0);
	
	int sideLength = ceil(sqrt(totalTextures));
	float uvDelta = 1.0 / sideLength;
	int nextTexture = mod(currentTexture + 1, totalTextures);
	
	currentCoords = texCoords * uvDelta;
	nextCoords = texCoords * uvDelta;
	currentCoords.x += mod(currentTexture, sideLength) * uvDelta;
	currentCoords.y += currentTexture / sideLength * uvDelta;
	nextCoords.x += mod(nextTexture, sideLength);
	nextCoords.y += nextTexture / sideLength * uvDelta;
}