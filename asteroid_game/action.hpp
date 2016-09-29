#ifndef ACTION_HPP
#define ACTION_HPP
#include <SFML/Graphics.hpp>

class Action : public sf::Drawable
{
	public:
		Action(int width, int height);
		~Action();
		void moveScene();
		void clicked();
		bool isGameOver();

	private:
		void virtual draw(sf::RenderTarget& target, sf::RenderStates states) const;
		void init();
		sf::Sprite loadSprite(std::string, bool resize);
		int getWidth(sf::Sprite s);
		int getHeight(sf::Sprite s);
		int _x, _y, _x2, _pos_y, _width, _height, acceleration;
		int _obs_x[3];
		int _obs_y[3];
		bool gameOver;
		sf::Sprite _obs[3];
		sf::Sprite _rise1, _rise2, _fall, _rise1_burn, _rise2_burn, _fall_burn, _background, _background2, _rock, _satellite, _boot ,_current, _gameover;
		sf::Font _font;
		sf::Text _text;
		sf::Texture _texture[7];
};
#endif
