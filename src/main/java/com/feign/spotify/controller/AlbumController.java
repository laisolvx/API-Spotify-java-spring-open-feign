package com.feign.spotify.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.feign.spotify.client.Album;
import com.feign.spotify.client.AlbumSpotifyClient;
import com.feign.spotify.client.AuthSpotifyClient;
import com.feign.spotify.client.LoginRequest;

@RestController
@RequestMapping("spotify/api")
public class AlbumController {

    private final AuthSpotifyClient authSpotifyClient;
    private final AlbumSpotifyClient albumSpotifyClient;
    
    //lendo as propriedades no controlador e injetando valores no arquivo de config
    @Value("${spotify.client-id}")
    private String clientId;
    
    @Value("${spotify.client-secret}")
    private String clientSecret;
    
    //comunicação com a API de autenticação e API de albuns do Spotify
    public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
        this.authSpotifyClient = authSpotifyClient;
        this.albumSpotifyClient = albumSpotifyClient;
    }
    
    //chamada da lista de albums a partir da API do Spotify
    @GetMapping("/albums")
    public ResponseEntity<List<Album>> helloWorld() {
    	
    	//autenticação no Spotify usando as credenciais do cliente
        var request = new LoginRequest("client_credentials", clientId, clientSecret);       
        // obtendo um token de acesso válido
        var token = authSpotifyClient.login(request).getAccessToken();
        //obtendo os lançamentos de albuns do Spotify, usando token de acesso p autenticar a requisição
        var response = albumSpotifyClient.getReleases("Bearer " + token);
        //resposta da requisição que é retornado como resposta para solicitação
        return ResponseEntity.ok(response.getAlbums().getItems());
    }
}
