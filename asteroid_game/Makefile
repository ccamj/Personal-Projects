all: ps2a

ps2a: main.o action.o
	g++ action.o main.o -o astro -lsfml-graphics -lsfml-window -lsfml-system

action.o: action.cpp action.hpp
	g++ -c action.cpp -Wall -Werror -ansi -pedantic

main.o: main.cpp action.hpp
	g++ -c main.cpp -Wall -Werror -ansi -pedantic

clean:
	rm -rf astro *.o *.~
