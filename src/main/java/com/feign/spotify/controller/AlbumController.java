package com.feign.spotify.controller;

	import java.util.List;

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
	
	
	public AlbumController(AuthSpotifyClient authSpotifyClient, AlbumSpotifyClient albumSpotifyClient) {
		this.authSpotifyClient = authSpotifyClient;
		this.albumSpotifyClient = albumSpotifyClient;
	}


	@GetMapping("/albums")
	public ResponseEntity<List<Album>> helloWorld() {
		var request = new LoginRequest( 
				"client_credentials",
				"2f259de2b7ce47729e2c7a82823440f1",
				"1467b29e0e7b460c9b70ef28e1311bed"
				);
		var token = authSpotifyClient.login(request).getAccessToken();
		
		var response = albumSpotifyClient.getReleases(" Bearer " + token);
		
		return ResponseEntity.ok(response.getAlbums().getItems());
	}
	
}
