%consult(KB).
end(0,0).
wall(1,0).
wall(1,1).
wall(1,3).
wall(1,4).
start(2,2).
at(2,2,11,7,s0).
wall(3,1).
wall(3,3).
wall(4,1).
wall(4,3).
pokemon(0,4,s0).
pokemon(2,0,s0).
pokemon(2,3,s0).
pokemon(3,0,s0).
pokemon(3,4,s0).
pokemon(4,0,s0).
pokemon(4,4,s0).

at(X,Y,H,P,result(A, S)):- at(X1, Y1, H1, P1, S),
			  ((pokemon(X,Y,S), A=collect(P1,S),P is P1-1, X1 = X, Y1 = Y, H1 = H);
		          (\+wall(X,Y),A=up(X1,Y,H1,S), X is X1-1, H is H1-1,X1>0, X1 =< 4, Y1 = Y, P1 = P);
                          (\+wall(X,Y),A=down(X1,Y,H1,S), X is X1+1, H is H1-1, X1<4, X1 >= 0 , Y1 = Y, P1 = P);
		          (\+wall(X,Y),A=right(X,Y1,H1,S), Y is Y1+1, H is H1-1,Y1<4, Y1 >= 0, X1 = X, P1 = P);
                          (\+wall(X,Y),A=left(X,Y1,H1,S), Y is Y1-1, H is H1-1,Y1>0, Y1 =< 4, X1 = X, P1 = P));
                          (at(X, Y, H, P, S),
			  ((\+pokemon(X,Y,S),A=collect(P,S));
                          (wall(X1,Y),A=up(X,Y,H,S), X is X1-1);
                          (wall(X1,Y),A=down(X,Y,H,S), X is X1+1);
                          (wall(X,Y1),A=right(X,Y,H,S), Y is Y1+1);
                          (wall(X,Y1),A=left(X,Y,H,S), Y is Y1-1))).

