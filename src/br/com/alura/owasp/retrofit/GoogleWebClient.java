package br.com.alura.owasp.retrofit;

import java.io.IOException;

import org.springframework.stereotype.Component;

import retrofit2.Call;

@Component
public class GoogleWebClient {

	private static final String SECRET = "6Ldm2PYZAAAAANFafvmUqjw1neNNfofAXLY70yIc";

	public boolean verifica(String recaptcha) throws IOException {
        Call<Resposta> token = new RetrofitInicializador().getGoogleService().enviaToken(SECRET, recaptcha);
        return token.execute().body().isSuccess();
    }
}
