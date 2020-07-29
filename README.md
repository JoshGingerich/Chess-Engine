# Chess Engine Project
This engine was created for a university computer science course as an honors contract project. The main purpose of the project was to develop a well-structured software program that would utilize interfaces to keep the program consistent and easier to modify. The other purpose of the project was to develop and work with an AI algorithm called alpha beta minimax. A GUI was created so that the user could intuitively interact with the chess engine.

The engine generates all possible future positions depending on the depth of search desired and uses alpha-beta pruning to intelligently reduce the total number of branches it has to evaluate. It then utilizes the minimax function to pick the best current move that maximizes its long term position.

The following image highlights the information the engine processes after any given move.

<img width="690" alt="Screen Shot 2020-02-22 at 7 44 22 PM" src="https://user-images.githubusercontent.com/61246608/75102160-d8e76280-55ac-11ea-8d5a-b4bc4dcbfdd4.png">



The image below shows the user interface of the program. The computer automatically moves after the user inputs a move by dragging and dropping a piece.

<img width="353" alt="Screen Shot 2020-02-22 at 4 14 02 PM" src="https://user-images.githubusercontent.com/61246608/75100184-3325fa80-5590-11ea-8b30-01702c1aeff1.png">


As the image below shows, this engine was able to beat a 1600 rated Stockfish engine, which roughly indicates the rating level of this engine. For comparison, the average rating for a human who plays chess regularly would aproximately be in the 1200-1500 range.

<img width="1141" alt="Screen Shot 2020-02-19 at 5 55 21 PM" src="https://user-images.githubusercontent.com/61246608/75100220-9748be80-5590-11ea-88e6-4e05f2196c58.png">
