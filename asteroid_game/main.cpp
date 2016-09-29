#include <SFML/Graphics.hpp>
#include <iostream>
#include <cmath>
#include "action.hpp"

int main(){
	#define width 550
	#define height 750
	sf::RenderWindow window(sf::VideoMode(width, height), "Astro Dodge");
	Action game(width, height);
	// main loop
	sf:: Texture texture;
	if(!texture.loadFromFile("./assets/astro_rise1.gif")){
		std::cout<< "failed" << std::endl;
		//return EXIT_FAILURE;
	}
	sf::Sprite sprite(texture);
	sprite.setPosition(50,50);
	while (window.isOpen()) {
		sf::Event event;
		while (window.pollEvent(event)) {
			if (event.type == sf::Event::Closed) {
				window.close();
				break;
			}
			if (event.type == sf::Event::MouseButtonPressed){
				if(!game.isGameOver())
					game.clicked();
			}
		}
		window.setFramerateLimit(50);
		window.clear();
		window.draw(game);
		game.moveScene();
		//window.draw(sprite);
		window.display();
	}
	return 0;
}
