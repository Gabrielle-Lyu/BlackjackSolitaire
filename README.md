# BlackjackSolitaire
Blackjack Solitaire game

--------------------------GAME RULES--------------------------

Implementation of Blackjack Solitare game on:
https://www.solitairenetwork.com/solitaire/blackjack-square-solitaire-game.html

Blackjack Solitaire is a one-player game with scoring rules inspired by the casino game of Blackjack,
where players attempt to build a hand with a value as close to 21 as possible without exceeding 21.


-----CARD VALUE-----

Each card has a value.
Cards with numbers on them (2s through 10s) have value equal to the number on the card.
“Face” cards (Kings, Queens, Jacks) are valued at 10 each.
Aces can be “high”, counting as value 11, or “low”, counting as value 1, depending on which value would give the player a higher score without exceeding 21.
Hands are calculated by summing the values of the cards in them.
Each hand, then, has a cumulative value.

The points that the player scores per hand are calculated as follows:

Hand Type	                Description of Hand	                    Point Value
Blackjack	                A hand of 2 cards that sum to 21	        10
21	                        3, 4, or 5 cards that sum to 21             7
20	                        Hand of any size that sums to 20	        5
19	                        Hand of any size that sums to 19	        4
18	                        Hand of any size that sums to 18	        3
17	                        Hand of any size that sums to 17	        2
<=16	                    Hand of any size that sums to 16 or less	1
BUST	                    Hand of any size that sums to 22 or more	0
