package shaders;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL20;

import maths.Mat4f;
import maths.Vec2f;
import maths.Vec3f;
import objects.RenderObject;
import renderEngine.Model;

public abstract class Shader<T extends RenderObject> {
	
	private int programID, vertexID, fragmentID;
	
	public Shader(String vertexShaderFile, String fragmentShaderFile){
		programID = GL20.glCreateProgram();
		vertexID = genShader(vertexShaderFile, GL20.GL_VERTEX_SHADER);
		fragmentID = genShader(fragmentShaderFile, GL20.GL_FRAGMENT_SHADER);
		
		GL20.glAttachShader(programID, vertexID);
		GL20.glAttachShader(programID, fragmentID);
		
		bindAttributes();
		
		GL20.glLinkProgram(programID);
		GL20.glValidateProgram(programID);
	}
	
	public abstract void bindAttributes();
	public abstract void prepareShaderRender();
	public abstract void prepareModelRender(Model model);
	public abstract void prepareObjectRender(T object);
	public abstract void prepareInstancedRender(List<T> instances, float[] instancedData);
	public abstract void finishModelRender(Model model);
	
	public void bindAttribute(int attribNumber, String attribName){
		GL20.glBindAttribLocation(programID, attribNumber, attribName);
	}
	
	public int getUniformLocation(String uniformName){
		return GL20.glGetUniformLocation(programID, uniformName);
	}
	
	public void start(){
		GL20.glUseProgram(programID);
	}
	
	public void stop(){
		GL20.glUseProgram(0);
	}
	
	public void delete(){
		stop();
		GL20.glDetachShader(programID, vertexID);
		GL20.glDetachShader(programID, fragmentID);
		GL20.glDeleteShader(vertexID);
		GL20.glDeleteShader(fragmentID);
		GL20.glDeleteProgram(programID);
	}
	
	public void loadInt(int location, int num){
		GL20.glUniform1i(location, num);
	}
	
	public void loadFloat(int location, float num){
		GL20.glUniform1f(location, num);
	}
	
	public void loadBoolean(int location, boolean value){
		loadFloat(location, value ? 1 : 0);
	}
	
	public void loadVec2f(int location, Vec2f vec){
		GL20.glUniform2f(location, vec.getX(), vec.getY());
	}
	
	public void loadVec3f(int location, Vec3f vec){
		GL20.glUniform3f(location, vec.getX(), vec.getY(), vec.getZ());
	}
	
	public void loadMat4f(int location, Mat4f mat){
		GL20.glUniformMatrix4(location, false, mat.toBuffer());
	}
	
	private static int genShader(String file, int type){
		StringBuilder assembler = new StringBuilder();
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String line;
			while((line = reader.readLine()) != null){
				assembler.append(line + "\n");
			}
			reader.close();
		} catch (IOException e) {
			System.err.println("Failed to read shader program");
			e.printStackTrace();
			System.exit(-1);
		}
		
		int shaderID = GL20.glCreateShader(type);
		GL20.glShaderSource(shaderID, assembler);
		GL20.glCompileShader(shaderID);
		if(GL20.glGetShaderi(shaderID, GL20.GL_COMPILE_STATUS) == GL11.GL_FALSE){
			System.err.println("Error in shader program, failed to compile shader:");
			System.out.println(GL20.glGetShaderInfoLog(shaderID, 1000));
			System.exit(-1);
		}
		
		return shaderID;
	}
	
}
