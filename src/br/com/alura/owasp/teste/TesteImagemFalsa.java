package br.com.alura.owasp.teste;

import java.io.IOException;

public class TesteImagemFalsa {

	public static void main(String[] args) {
		try {
			//Windows
			//Runtime.getRuntime().exec("cmd.exe /c cd C:\\ && rmdir imagemfalsa");
			
			//Linux
			ProcessBuilder builder = new ProcessBuilder(
			         "/bin/bash", "-c", "cd /home/ubirailson && rm -r imagemfalsa");
			        builder.start();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
