#include <SFML/Graphics.hpp>
#include <cmath>
#include "action.hpp"
#include <iostream>


void Action::draw(sf::RenderTarget& target, sf::RenderStates states) const {
	target.draw(_background,states);
	target.draw(_background2,states);
	target.draw(_current,states);
	for(int i=0;i<3;i++)
		target.draw(_obs[i], states);
	if(gameOver)
		target.draw(_gameover, states);
	
}

Action::Action(int width, int height):
_width(width), _height(height){
	init();

}

void Action::init(){
	gameOver = false;
	_rise1 = loadSprite("./assets/astro_rise1.png",true);
	_rise2 = loadSprite("./assets/astro_rise2.png",true);
	_fall = loadSprite("./assets/astro_fall.png",true);
	_rise1_burn = loadSprite("./assets/astro_rise1_burn.png",true);
	_rise2_burn = loadSprite("./assets/astro_rise2_burn.png",true);
	_fall_burn = loadSprite("./assets/astro_fall_burn.png",true);
	_background = loadSprite("./assets/background.png",false);
	_background2 = loadSprite("./assets/background.png",false);
	_rock = loadSprite("./assets/rock.png",true);
	_satellite = loadSprite("./assets/satellite.png",true);
	_boot = loadSprite("./assets/boot.png",true);
	_gameover = loadSprite("./assets/gameover.png",true);

	_background.setPosition(0,0);
	_background2.setPosition(_background2.getLocalBounds().width,0);
	_x=0;
	_x2 = _background2.getLocalBounds().width;
	_pos_y = _height/5;
	_current.setPosition(15, 0);
	acceleration = 0;

	_obs[0] = _rock;
	_obs[1] = _rock;
	_obs[2] = _rock;
	_obs_x[0] = _width;
	_obs_y[0] = 300;
	_obs_x[1] = _width + getWidth(_rock) + 100;
	_obs_y[1] = 20;
	_obs_x[2] = _width + getWidth(_rock) + 200;
	_obs_y[2] = 550;
	_obs[0].setPosition(_obs_x[0], _obs_y[0]);
	_obs[1].setPosition(_obs_x[1], _obs_y[2]);
	_obs[2].setPosition(_obs_x[2], _obs_y[3]);

	_current = _fall; // set the current sprite state

	if(!_font.loadFromFile("/assets/font.ttf"))
		std::cout<< "failed to load: \"/assets/font.ttf\"" << std::endl;
	//sf::Text text("", _font, 20);
	_text.setFont(_font);
	_text.setCharacterSize(80);
	_text.setColor(sf::Color::White);
}

sf::Sprite Action::loadSprite(std::string path, bool resize){
	static int i;
	// Load a sprite
	if(!_texture[i].loadFromFile(path)){
		std::cout<< "failed to load: \"" << path << "\"" << std::endl;
	}
	sf::Sprite sprite(_texture[i]);
	if(resize){
		sprite.setPosition(15,_height/5);
		//sprite.resize(sprite.getLocalBounds().width * .7, sprite.getLocalBounds().height * .7)
		sprite.setScale(.7,.7);
	}
	i++;
	return sprite;
}
void Action::moveScene(){
	
	// animate character
	_current.setPosition(15, _pos_y);
	if(_pos_y < 510){
		if(acceleration > 40)
			_current = _fall;
		else if(acceleration > 30)
			_current = _rise1;
	}
	else{
		if(acceleration > 40)
			_current = _fall_burn;
		else if(acceleration > 30)
			_current = _rise1_burn;
	}
	_current.setPosition(15, _pos_y);
	// character falling
	if(_pos_y < _height - getWidth(_current) && !gameOver){
		acceleration++;
		_current.setPosition(15, _pos_y+=acceleration/8);
	}
	else gameOver = true;
	// moving objects
	static int index = 0; //static: declare and assign once.
	
	_obs_x[index]-=6;
	if(_obs_x[index] < -getWidth(_obs[index])){
		_obs_x[index] = _width;
		std::srand(time(NULL));
		int rand = std::rand() % 20 + 1; // random int between 1 and 20
		
		if(rand<16)	// random rock, sat, or boot
			_obs[index] = _rock;
		else if (rand <18)
			_obs[index] = _satellite;
		else
			_obs[index] = _boot;
		// set random Y coord
		if(rand < 5){ _obs_y[index] = 0;}
		else if(rand < 9){ _obs_y[index] = 150;}
		else if(rand < 13){ _obs_y[index] = 300;}
		else if(rand < 17){ _obs_y[index] = 450;}
		else{ _obs_y[index] = 600;}
		
	}
	_obs[index].setPosition(_obs_x[index], _obs_y[index]);
	//check if objects hit character
	if(_obs_x[index] <= 15 + getWidth(_current) && _obs_x[index]+getWidth(_obs[index]) >= 15){
		if(_obs_y[index] <= _pos_y + getHeight(_current) && _obs_y[index]+getHeight(_obs[index]) >= _pos_y){
			gameOver = true;
			_gameover.setPosition(_width/2 - getWidth(_gameover)/2, _height/2 - getHeight(_gameover)/2);
			_text.setString("GAME OVER");
			_text.setPosition(_width/2 - _text.getLocalBounds().width/2, 
			_height/2 - _text.getLocalBounds().height/2);
		}
	}
	//debugging
	//std::cout << _obs_y[0] << "," << _obs_y[1] << "," << _obs_y[2] << std::endl;
	if(index < 2){
		index++;
	}
	else{ index = 0;}
	
	

	//background moving
	_background.setPosition(_x--,0);
	if(_x < -_width){
		_x = _width-1;
		_background.setPosition(_x,0);
	}
	_background2.setPosition(_x2--,0);
	if(_x2 < -_width){
		_x2 = _width-1;
		_background2.setPosition(_x2,0);
	}
}
int Action::getHeight(sf::Sprite sprite){
	sf::Vector2f scale = sprite.getScale();
	return sprite.getLocalBounds().height * scale.y;
}
int Action::getWidth(sf::Sprite sprite){
	sf::Vector2f scale = sprite.getScale();
	return sprite.getLocalBounds().width * scale.x;
}
bool Action::isGameOver(){
	return gameOver;
}
void Action::clicked(){
	if(_pos_y < 510) // above the sun
		_current = _rise2;
	else _current = _rise2_burn;
	acceleration = 15;
	_current.setPosition(15, _pos_y-=65);
	if(_pos_y < 0) _current.setPosition(15, _pos_y = 0);
	
}
Action::~Action(){}
