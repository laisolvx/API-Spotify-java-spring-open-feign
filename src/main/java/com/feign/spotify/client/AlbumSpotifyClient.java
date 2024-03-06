package com.feign.spotify.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(
		name = "AlbumSpotifyClient",
		url = "${spotify.url}"
		)
public interface AlbumSpotifyClient {
	
	@GetMapping(value = "/v1/browse/new-releases")
	AlbumResponse getReleases(@RequestHeader("Authorization") String authorization);

}
