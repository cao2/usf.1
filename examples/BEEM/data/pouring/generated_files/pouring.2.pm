
chan pour_empty1 =[0] of {int};
chan pour_full1 =[0] of {int};
chan pour_empty2 =[0] of {int};
chan pour_full2 =[0] of {int};
chan pour_empty3 =[0] of {int};
chan pour_full3 =[0] of {int};
chan pour_empty4 =[0] of {int};
chan pour_full4 =[0] of {int};
chan pour_empty5 =[0] of {int};
chan pour_full5 =[0] of {int};
chan pour_empty6 =[0] of {int};
chan pour_full6 =[0] of {int};
chan pour_empty7 =[0] of {int};
chan pour_full7 =[0] of {int};
chan pour_empty8 =[0] of {int};
chan pour_full8 =[0] of {int};
chan pour_empty9 =[0] of {int};
chan pour_full9 =[0] of {int};
chan pour_empty10 =[0] of {int};
chan pour_full10 =[0] of {int};
chan pour_empty11 =[0] of {int};
chan pour_full11 =[0] of {int};
chan pour_empty12 =[0] of {int};
chan pour_full12 =[0] of {int};
chan pour_empty13 =[0] of {int};
chan pour_full13 =[0] of {int};
chan pour_empty14 =[0] of {int};
chan pour_full14 =[0] of {int};
chan pour_empty15 =[0] of {int};
chan pour_full15 =[0] of {int};
chan pour_empty16 =[0] of {int};
chan pour_full16 =[0] of {int};
chan pour_empty17 =[0] of {int};
chan pour_full17 =[0] of {int};
chan pour_empty18 =[0] of {int};
chan pour_full18 =[0] of {int};
chan pour_empty19 =[0] of {int};
chan pour_full19 =[0] of {int};
chan pour_empty20 =[0] of {int};
chan pour_full20 =[0] of {int};
chan pour_empty21 =[0] of {int};
chan pour_full21 =[0] of {int};
chan pour_empty22 =[0] of {int};
chan pour_full22 =[0] of {int};
chan pour_empty23 =[0] of {int};
chan pour_full23 =[0] of {int};
chan pour_empty24 =[0] of {int};
chan pour_full24 =[0] of {int};
chan pour_empty25 =[0] of {int};
chan pour_full25 =[0] of {int};
chan pour_empty26 =[0] of {int};
chan pour_full26 =[0] of {int};
chan pour_empty27 =[0] of {int};
chan pour_full27 =[0] of {int};
chan pour_empty28 =[0] of {int};
chan pour_full28 =[0] of {int};
chan pour_empty29 =[0] of {int};
chan pour_full29 =[0] of {int};
chan pour_empty30 =[0] of {int};
chan pour_full30 =[0] of {int};

active proctype Bottle_1() { 

q0: if
:: pour_empty1?0; goto q1; 

:: pour_empty2?0; goto q2; 

:: pour_empty3?0; goto q3; 

:: pour_empty4?0; goto q4; 

:: pour_empty5?0; goto q5; 

:: pour_empty6?0; goto q6; 

:: pour_empty7?0; goto q7; 

:: pour_empty8?0; goto q8; 

:: pour_empty9?0; goto q9; 

:: pour_empty10?0; goto q10; 

:: pour_full10?0; goto q10; 

fi;
q1: if
:: pour_empty1!0; goto q0; 

:: pour_empty1?0; goto q2; 

:: pour_empty2?0; goto q3; 

:: pour_empty3?0; goto q4; 

:: pour_empty4?0; goto q5; 

:: pour_empty5?0; goto q6; 

:: pour_empty6?0; goto q7; 

:: pour_empty7?0; goto q8; 

:: pour_empty8?0; goto q9; 

:: pour_empty9?0; goto q10; 

:: pour_full1!0; goto q0; 

:: pour_full9?0; goto q10; 

fi;
q2: if
:: pour_empty2!0; goto q0; 

:: pour_empty1?0; goto q3; 

:: pour_empty2?0; goto q4; 

:: pour_empty3?0; goto q5; 

:: pour_empty4?0; goto q6; 

:: pour_empty5?0; goto q7; 

:: pour_empty6?0; goto q8; 

:: pour_empty7?0; goto q9; 

:: pour_empty8?0; goto q10; 

:: pour_full2!0; goto q0; 

:: pour_full1!0; goto q1; 

:: pour_full8?0; goto q10; 

fi;
q3: if
:: pour_empty3!0; goto q0; 

:: pour_empty1?0; goto q4; 

:: pour_empty2?0; goto q5; 

:: pour_empty3?0; goto q6; 

:: pour_empty4?0; goto q7; 

:: pour_empty5?0; goto q8; 

:: pour_empty6?0; goto q9; 

:: pour_empty7?0; goto q10; 

:: pour_full3!0; goto q0; 

:: pour_full2!0; goto q1; 

:: pour_full1!0; goto q2; 

:: pour_full7?0; goto q10; 

fi;
q4: if
:: pour_empty4!0; goto q0; 

:: pour_empty1?0; goto q5; 

:: pour_empty2?0; goto q6; 

:: pour_empty3?0; goto q7; 

:: pour_empty4?0; goto q8; 

:: pour_empty5?0; goto q9; 

:: pour_empty6?0; goto q10; 

:: pour_full4!0; goto q0; 

:: pour_full3!0; goto q1; 

:: pour_full2!0; goto q2; 

:: pour_full1!0; goto q3; 

:: pour_full6?0; goto q10; 

fi;
q5: if
:: pour_empty5!0; goto q0; 

:: pour_empty1?0; goto q6; 

:: pour_empty2?0; goto q7; 

:: pour_empty3?0; goto q8; 

:: pour_empty4?0; goto q9; 

:: pour_empty5?0; goto q10; 

:: pour_full5!0; goto q0; 

:: pour_full4!0; goto q1; 

:: pour_full3!0; goto q2; 

:: pour_full2!0; goto q3; 

:: pour_full1!0; goto q4; 

:: pour_full5?0; goto q10; 

fi;
q6: if
:: pour_empty6!0; goto q0; 

:: pour_empty1?0; goto q7; 

:: pour_empty2?0; goto q8; 

:: pour_empty3?0; goto q9; 

:: pour_empty4?0; goto q10; 

:: pour_full6!0; goto q0; 

:: pour_full5!0; goto q1; 

:: pour_full4!0; goto q2; 

:: pour_full3!0; goto q3; 

:: pour_full2!0; goto q4; 

:: pour_full1!0; goto q5; 

:: pour_full4?0; goto q10; 

fi;
q7: if
:: pour_empty7!0; goto q0; 

:: pour_empty1?0; goto q8; 

:: pour_empty2?0; goto q9; 

:: pour_empty3?0; goto q10; 

:: pour_full7!0; goto q0; 

:: pour_full6!0; goto q1; 

:: pour_full5!0; goto q2; 

:: pour_full4!0; goto q3; 

:: pour_full3!0; goto q4; 

:: pour_full2!0; goto q5; 

:: pour_full1!0; goto q6; 

:: pour_full3?0; goto q10; 

fi;
q8: if
:: pour_empty8!0; goto q0; 

:: pour_empty1?0; goto q9; 

:: pour_empty2?0; goto q10; 

:: pour_full8!0; goto q0; 

:: pour_full7!0; goto q1; 

:: pour_full6!0; goto q2; 

:: pour_full5!0; goto q3; 

:: pour_full4!0; goto q4; 

:: pour_full3!0; goto q5; 

:: pour_full2!0; goto q6; 

:: pour_full1!0; goto q7; 

:: pour_full2?0; goto q10; 

fi;
q9: if
:: pour_empty9!0; goto q0; 

:: pour_empty1?0; goto q10; 

:: pour_full9!0; goto q0; 

:: pour_full8!0; goto q1; 

:: pour_full7!0; goto q2; 

:: pour_full6!0; goto q3; 

:: pour_full5!0; goto q4; 

:: pour_full4!0; goto q5; 

:: pour_full3!0; goto q6; 

:: pour_full2!0; goto q7; 

:: pour_full1!0; goto q8; 

:: pour_full1?0; goto q10; 

fi;
q10: if
:: pour_empty10!0; goto q0; 

:: pour_full10!0; goto q0; 

:: pour_full9!0; goto q1; 

:: pour_full8!0; goto q2; 

:: pour_full7!0; goto q3; 

:: pour_full6!0; goto q4; 

:: pour_full5!0; goto q5; 

:: pour_full4!0; goto q6; 

:: pour_full3!0; goto q7; 

:: pour_full2!0; goto q8; 

:: pour_full1!0; goto q9; 

fi;
}

active proctype Bottle_2() { 

q0: if
:: pour_empty1?0; goto q1; 

:: pour_empty2?0; goto q2; 

:: pour_empty3?0; goto q3; 

:: pour_empty4?0; goto q4; 

:: pour_empty5?0; goto q5; 

:: pour_empty6?0; goto q6; 

:: pour_empty7?0; goto q7; 

:: pour_empty8?0; goto q8; 

:: pour_full8?0; goto q8; 

fi;
q1: if
:: pour_empty1!0; goto q0; 

:: pour_empty1?0; goto q2; 

:: pour_empty2?0; goto q3; 

:: pour_empty3?0; goto q4; 

:: pour_empty4?0; goto q5; 

:: pour_empty5?0; goto q6; 

:: pour_empty6?0; goto q7; 

:: pour_empty7?0; goto q8; 

:: pour_full1!0; goto q0; 

:: pour_full7?0; goto q8; 

fi;
q2: if
:: pour_empty2!0; goto q0; 

:: pour_empty1?0; goto q3; 

:: pour_empty2?0; goto q4; 

:: pour_empty3?0; goto q5; 

:: pour_empty4?0; goto q6; 

:: pour_empty5?0; goto q7; 

:: pour_empty6?0; goto q8; 

:: pour_full2!0; goto q0; 

:: pour_full1!0; goto q1; 

:: pour_full6?0; goto q8; 

fi;
q3: if
:: pour_empty3!0; goto q0; 

:: pour_empty1?0; goto q4; 

:: pour_empty2?0; goto q5; 

:: pour_empty3?0; goto q6; 

:: pour_empty4?0; goto q7; 

:: pour_empty5?0; goto q8; 

:: pour_full3!0; goto q0; 

:: pour_full2!0; goto q1; 

:: pour_full1!0; goto q2; 

:: pour_full5?0; goto q8; 

fi;
q4: if
:: pour_empty4!0; goto q0; 

:: pour_empty1?0; goto q5; 

:: pour_empty2?0; goto q6; 

:: pour_empty3?0; goto q7; 

:: pour_empty4?0; goto q8; 

:: pour_full4!0; goto q0; 

:: pour_full3!0; goto q1; 

:: pour_full2!0; goto q2; 

:: pour_full1!0; goto q3; 

:: pour_full4?0; goto q8; 

fi;
q5: if
:: pour_empty5!0; goto q0; 

:: pour_empty1?0; goto q6; 

:: pour_empty2?0; goto q7; 

:: pour_empty3?0; goto q8; 

:: pour_full5!0; goto q0; 

:: pour_full4!0; goto q1; 

:: pour_full3!0; goto q2; 

:: pour_full2!0; goto q3; 

:: pour_full1!0; goto q4; 

:: pour_full3?0; goto q8; 

fi;
q6: if
:: pour_empty6!0; goto q0; 

:: pour_empty1?0; goto q7; 

:: pour_empty2?0; goto q8; 

:: pour_full6!0; goto q0; 

:: pour_full5!0; goto q1; 

:: pour_full4!0; goto q2; 

:: pour_full3!0; goto q3; 

:: pour_full2!0; goto q4; 

:: pour_full1!0; goto q5; 

:: pour_full2?0; goto q8; 

fi;
q7: if
:: pour_empty7!0; goto q0; 

:: pour_empty1?0; goto q8; 

:: pour_full7!0; goto q0; 

:: pour_full6!0; goto q1; 

:: pour_full5!0; goto q2; 

:: pour_full4!0; goto q3; 

:: pour_full3!0; goto q4; 

:: pour_full2!0; goto q5; 

:: pour_full1!0; goto q6; 

:: pour_full1?0; goto q8; 

fi;
q8: if
:: pour_empty8!0; goto q0; 

:: pour_full8!0; goto q0; 

:: pour_full7!0; goto q1; 

:: pour_full6!0; goto q2; 

:: pour_full5!0; goto q3; 

:: pour_full4!0; goto q4; 

:: pour_full3!0; goto q5; 

:: pour_full2!0; goto q6; 

:: pour_full1!0; goto q7; 

fi;
}

active proctype Bottle_3() { 

q0: if
:: pour_empty1?0; goto q1; 

:: pour_empty2?0; goto q2; 

:: pour_empty3?0; goto q3; 

:: pour_empty4?0; goto q4; 

:: pour_empty5?0; goto q5; 

:: pour_empty6?0; goto q6; 

:: pour_empty7?0; goto q7; 

:: pour_empty8?0; goto q8; 

:: pour_empty9?0; goto q9; 

:: pour_empty10?0; goto q10; 

:: pour_empty11?0; goto q11; 

:: pour_empty12?0; goto q12; 

:: pour_empty13?0; goto q13; 

:: pour_empty14?0; goto q14; 

:: pour_full14?0; goto q14; 

fi;
q1: if
:: pour_empty1!0; goto q0; 

:: pour_empty1?0; goto q2; 

:: pour_empty2?0; goto q3; 

:: pour_empty3?0; goto q4; 

:: pour_empty4?0; goto q5; 

:: pour_empty5?0; goto q6; 

:: pour_empty6?0; goto q7; 

:: pour_empty7?0; goto q8; 

:: pour_empty8?0; goto q9; 

:: pour_empty9?0; goto q10; 

:: pour_empty10?0; goto q11; 

:: pour_empty11?0; goto q12; 

:: pour_empty12?0; goto q13; 

:: pour_empty13?0; goto q14; 

:: pour_full1!0; goto q0; 

:: pour_full13?0; goto q14; 

fi;
q2: if
:: pour_empty2!0; goto q0; 

:: pour_empty1?0; goto q3; 

:: pour_empty2?0; goto q4; 

:: pour_empty3?0; goto q5; 

:: pour_empty4?0; goto q6; 

:: pour_empty5?0; goto q7; 

:: pour_empty6?0; goto q8; 

:: pour_empty7?0; goto q9; 

:: pour_empty8?0; goto q10; 

:: pour_empty9?0; goto q11; 

:: pour_empty10?0; goto q12; 

:: pour_empty11?0; goto q13; 

:: pour_empty12?0; goto q14; 

:: pour_full2!0; goto q0; 

:: pour_full1!0; goto q1; 

:: pour_full12?0; goto q14; 

fi;
q3: if
:: pour_empty3!0; goto q0; 

:: pour_empty1?0; goto q4; 

:: pour_empty2?0; goto q5; 

:: pour_empty3?0; goto q6; 

:: pour_empty4?0; goto q7; 

:: pour_empty5?0; goto q8; 

:: pour_empty6?0; goto q9; 

:: pour_empty7?0; goto q10; 

:: pour_empty8?0; goto q11; 

:: pour_empty9?0; goto q12; 

:: pour_empty10?0; goto q13; 

:: pour_empty11?0; goto q14; 

:: pour_full3!0; goto q0; 

:: pour_full2!0; goto q1; 

:: pour_full1!0; goto q2; 

:: pour_full11?0; goto q14; 

fi;
q4: if
:: pour_empty4!0; goto q0; 

:: pour_empty1?0; goto q5; 

:: pour_empty2?0; goto q6; 

:: pour_empty3?0; goto q7; 

:: pour_empty4?0; goto q8; 

:: pour_empty5?0; goto q9; 

:: pour_empty6?0; goto q10; 

:: pour_empty7?0; goto q11; 

:: pour_empty8?0; goto q12; 

:: pour_empty9?0; goto q13; 

:: pour_empty10?0; goto q14; 

:: pour_full4!0; goto q0; 

:: pour_full3!0; goto q1; 

:: pour_full2!0; goto q2; 

:: pour_full1!0; goto q3; 

:: pour_full10?0; goto q14; 

fi;
q5: if
:: pour_empty5!0; goto q0; 

:: pour_empty1?0; goto q6; 

:: pour_empty2?0; goto q7; 

:: pour_empty3?0; goto q8; 

:: pour_empty4?0; goto q9; 

:: pour_empty5?0; goto q10; 

:: pour_empty6?0; goto q11; 

:: pour_empty7?0; goto q12; 

:: pour_empty8?0; goto q13; 

:: pour_empty9?0; goto q14; 

:: pour_full5!0; goto q0; 

:: pour_full4!0; goto q1; 

:: pour_full3!0; goto q2; 

:: pour_full2!0; goto q3; 

:: pour_full1!0; goto q4; 

:: pour_full9?0; goto q14; 

fi;
q6: if
:: pour_empty6!0; goto q0; 

:: pour_empty1?0; goto q7; 

:: pour_empty2?0; goto q8; 

:: pour_empty3?0; goto q9; 

:: pour_empty4?0; goto q10; 

:: pour_empty5?0; goto q11; 

:: pour_empty6?0; goto q12; 

:: pour_empty7?0; goto q13; 

:: pour_empty8?0; goto q14; 

:: pour_full6!0; goto q0; 

:: pour_full5!0; goto q1; 

:: pour_full4!0; goto q2; 

:: pour_full3!0; goto q3; 

:: pour_full2!0; goto q4; 

:: pour_full1!0; goto q5; 

:: pour_full8?0; goto q14; 

fi;
q7: if
:: pour_empty7!0; goto q0; 

:: pour_empty1?0; goto q8; 

:: pour_empty2?0; goto q9; 

:: pour_empty3?0; goto q10; 

:: pour_empty4?0; goto q11; 

:: pour_empty5?0; goto q12; 

:: pour_empty6?0; goto q13; 

:: pour_empty7?0; goto q14; 

:: pour_full7!0; goto q0; 

:: pour_full6!0; goto q1; 

:: pour_full5!0; goto q2; 

:: pour_full4!0; goto q3; 

:: pour_full3!0; goto q4; 

:: pour_full2!0; goto q5; 

:: pour_full1!0; goto q6; 

:: pour_full7?0; goto q14; 

fi;
q8: if
:: pour_empty8!0; goto q0; 

:: pour_empty1?0; goto q9; 

:: pour_empty2?0; goto q10; 

:: pour_empty3?0; goto q11; 

:: pour_empty4?0; goto q12; 

:: pour_empty5?0; goto q13; 

:: pour_empty6?0; goto q14; 

:: pour_full8!0; goto q0; 

:: pour_full7!0; goto q1; 

:: pour_full6!0; goto q2; 

:: pour_full5!0; goto q3; 

:: pour_full4!0; goto q4; 

:: pour_full3!0; goto q5; 

:: pour_full2!0; goto q6; 

:: pour_full1!0; goto q7; 

:: pour_full6?0; goto q14; 

fi;
q9: if
:: pour_empty9!0; goto q0; 

:: pour_empty1?0; goto q10; 

:: pour_empty2?0; goto q11; 

:: pour_empty3?0; goto q12; 

:: pour_empty4?0; goto q13; 

:: pour_empty5?0; goto q14; 

:: pour_full9!0; goto q0; 

:: pour_full8!0; goto q1; 

:: pour_full7!0; goto q2; 

:: pour_full6!0; goto q3; 

:: pour_full5!0; goto q4; 

:: pour_full4!0; goto q5; 

:: pour_full3!0; goto q6; 

:: pour_full2!0; goto q7; 

:: pour_full1!0; goto q8; 

:: pour_full5?0; goto q14; 

fi;
q10: if
:: pour_empty10!0; goto q0; 

:: pour_empty1?0; goto q11; 

:: pour_empty2?0; goto q12; 

:: pour_empty3?0; goto q13; 

:: pour_empty4?0; goto q14; 

:: pour_full10!0; goto q0; 

:: pour_full9!0; goto q1; 

:: pour_full8!0; goto q2; 

:: pour_full7!0; goto q3; 

:: pour_full6!0; goto q4; 

:: pour_full5!0; goto q5; 

:: pour_full4!0; goto q6; 

:: pour_full3!0; goto q7; 

:: pour_full2!0; goto q8; 

:: pour_full1!0; goto q9; 

:: pour_full4?0; goto q14; 

fi;
q11: if
:: pour_empty11!0; goto q0; 

:: pour_empty1?0; goto q12; 

:: pour_empty2?0; goto q13; 

:: pour_empty3?0; goto q14; 

:: pour_full11!0; goto q0; 

:: pour_full10!0; goto q1; 

:: pour_full9!0; goto q2; 

:: pour_full8!0; goto q3; 

:: pour_full7!0; goto q4; 

:: pour_full6!0; goto q5; 

:: pour_full5!0; goto q6; 

:: pour_full4!0; goto q7; 

:: pour_full3!0; goto q8; 

:: pour_full2!0; goto q9; 

:: pour_full1!0; goto q10; 

:: pour_full3?0; goto q14; 

fi;
q12: if
:: pour_empty12!0; goto q0; 

:: pour_empty1?0; goto q13; 

:: pour_empty2?0; goto q14; 

:: pour_full12!0; goto q0; 

:: pour_full11!0; goto q1; 

:: pour_full10!0; goto q2; 

:: pour_full9!0; goto q3; 

:: pour_full8!0; goto q4; 

:: pour_full7!0; goto q5; 

:: pour_full6!0; goto q6; 

:: pour_full5!0; goto q7; 

:: pour_full4!0; goto q8; 

:: pour_full3!0; goto q9; 

:: pour_full2!0; goto q10; 

:: pour_full1!0; goto q11; 

:: pour_full2?0; goto q14; 

fi;
q13: if
:: pour_empty13!0; goto q0; 

:: pour_empty1?0; goto q14; 

:: pour_full13!0; goto q0; 

:: pour_full12!0; goto q1; 

:: pour_full11!0; goto q2; 

:: pour_full10!0; goto q3; 

:: pour_full9!0; goto q4; 

:: pour_full8!0; goto q5; 

:: pour_full7!0; goto q6; 

:: pour_full6!0; goto q7; 

:: pour_full5!0; goto q8; 

:: pour_full4!0; goto q9; 

:: pour_full3!0; goto q10; 

:: pour_full2!0; goto q11; 

:: pour_full1!0; goto q12; 

:: pour_full1?0; goto q14; 

fi;
q14: if
:: pour_empty14!0; goto q0; 

:: pour_full14!0; goto q0; 

:: pour_full13!0; goto q1; 

:: pour_full12!0; goto q2; 

:: pour_full11!0; goto q3; 

:: pour_full10!0; goto q4; 

:: pour_full9!0; goto q5; 

:: pour_full8!0; goto q6; 

:: pour_full7!0; goto q7; 

:: pour_full6!0; goto q8; 

:: pour_full5!0; goto q9; 

:: pour_full4!0; goto q10; 

:: pour_full3!0; goto q11; 

:: pour_full2!0; goto q12; 

:: pour_full1!0; goto q13; 

fi;
}

active proctype Bottle_4() { 

q0: if
:: pour_empty1?0; goto q1; 

:: pour_empty2?0; goto q2; 

:: pour_empty3?0; goto q3; 

:: pour_empty4?0; goto q4; 

:: pour_empty5?0; goto q5; 

:: pour_empty6?0; goto q6; 

:: pour_empty7?0; goto q7; 

:: pour_full7?0; goto q7; 

fi;
q1: if
:: pour_empty1!0; goto q0; 

:: pour_empty1?0; goto q2; 

:: pour_empty2?0; goto q3; 

:: pour_empty3?0; goto q4; 

:: pour_empty4?0; goto q5; 

:: pour_empty5?0; goto q6; 

:: pour_empty6?0; goto q7; 

:: pour_full1!0; goto q0; 

:: pour_full6?0; goto q7; 

fi;
q2: if
:: pour_empty2!0; goto q0; 

:: pour_empty1?0; goto q3; 

:: pour_empty2?0; goto q4; 

:: pour_empty3?0; goto q5; 

:: pour_empty4?0; goto q6; 

:: pour_empty5?0; goto q7; 

:: pour_full2!0; goto q0; 

:: pour_full1!0; goto q1; 

:: pour_full5?0; goto q7; 

fi;
q3: if
:: pour_empty3!0; goto q0; 

:: pour_empty1?0; goto q4; 

:: pour_empty2?0; goto q5; 

:: pour_empty3?0; goto q6; 

:: pour_empty4?0; goto q7; 

:: pour_full3!0; goto q0; 

:: pour_full2!0; goto q1; 

:: pour_full1!0; goto q2; 

:: pour_full4?0; goto q7; 

fi;
q4: if
:: pour_empty4!0; goto q0; 

:: pour_empty1?0; goto q5; 

:: pour_empty2?0; goto q6; 

:: pour_empty3?0; goto q7; 

:: pour_full4!0; goto q0; 

:: pour_full3!0; goto q1; 

:: pour_full2!0; goto q2; 

:: pour_full1!0; goto q3; 

:: pour_full3?0; goto q7; 

fi;
q5: if
:: pour_empty5!0; goto q0; 

:: pour_empty1?0; goto q6; 

:: pour_empty2?0; goto q7; 

:: pour_full5!0; goto q0; 

:: pour_full4!0; goto q1; 

:: pour_full3!0; goto q2; 

:: pour_full2!0; goto q3; 

:: pour_full1!0; goto q4; 

:: pour_full2?0; goto q7; 

fi;
q6: if
:: pour_empty6!0; goto q0; 

:: pour_empty1?0; goto q7; 

:: pour_full6!0; goto q0; 

:: pour_full5!0; goto q1; 

:: pour_full4!0; goto q2; 

:: pour_full3!0; goto q3; 

:: pour_full2!0; goto q4; 

:: pour_full1!0; goto q5; 

:: pour_full1?0; goto q7; 

fi;
q7: if
:: pour_empty7!0; goto q0; 

:: pour_full7!0; goto q0; 

:: pour_full6!0; goto q1; 

:: pour_full5!0; goto q2; 

:: pour_full4!0; goto q3; 

:: pour_full3!0; goto q4; 

:: pour_full2!0; goto q5; 

:: pour_full1!0; goto q6; 

fi;
}

active proctype Bottle_5() { 

q0: if
:: pour_empty1?0; goto q1; 

:: pour_empty2?0; goto q2; 

:: pour_empty3?0; goto q3; 

:: pour_empty4?0; goto q4; 

:: pour_empty5?0; goto q5; 

:: pour_full5?0; goto q5; 

fi;
q1: if
:: pour_empty1!0; goto q0; 

:: pour_empty1?0; goto q2; 

:: pour_empty2?0; goto q3; 

:: pour_empty3?0; goto q4; 

:: pour_empty4?0; goto q5; 

:: pour_full1!0; goto q0; 

:: pour_full4?0; goto q5; 

fi;
q2: if
:: pour_empty2!0; goto q0; 

:: pour_empty1?0; goto q3; 

:: pour_empty2?0; goto q4; 

:: pour_empty3?0; goto q5; 

:: pour_full2!0; goto q0; 

:: pour_full1!0; goto q1; 

:: pour_full3?0; goto q5; 

fi;
q3: if
:: pour_empty3!0; goto q0; 

:: pour_empty1?0; goto q4; 

:: pour_empty2?0; goto q5; 

:: pour_full3!0; goto q0; 

:: pour_full2!0; goto q1; 

:: pour_full1!0; goto q2; 

:: pour_full2?0; goto q5; 

fi;
q4: if
:: pour_empty4!0; goto q0; 

:: pour_empty1?0; goto q5; 

:: pour_full4!0; goto q0; 

:: pour_full3!0; goto q1; 

:: pour_full2!0; goto q2; 

:: pour_full1!0; goto q3; 

:: pour_full1?0; goto q5; 

fi;
q5: if
:: pour_empty5!0; goto q0; 

:: pour_full5!0; goto q0; 

:: pour_full4!0; goto q1; 

:: pour_full3!0; goto q2; 

:: pour_full2!0; goto q3; 

:: pour_full1!0; goto q4; 

fi;
}

active proctype Source() { 

q: if
:: pour_full1!0; goto q; 

:: pour_full2!0; goto q; 

:: pour_full3!0; goto q; 

:: pour_full4!0; goto q; 

:: pour_full5!0; goto q; 

:: pour_full6!0; goto q; 

:: pour_full7!0; goto q; 

:: pour_full8!0; goto q; 

:: pour_full9!0; goto q; 

:: pour_full10!0; goto q; 

:: pour_full11!0; goto q; 

:: pour_full12!0; goto q; 

:: pour_full13!0; goto q; 

:: pour_full14!0; goto q; 

:: pour_full15!0; goto q; 

:: pour_full16!0; goto q; 

:: pour_full17!0; goto q; 

:: pour_full18!0; goto q; 

:: pour_full19!0; goto q; 

:: pour_full20!0; goto q; 

:: pour_full21!0; goto q; 

:: pour_full22!0; goto q; 

:: pour_full23!0; goto q; 

:: pour_full24!0; goto q; 

:: pour_full25!0; goto q; 

:: pour_full26!0; goto q; 

:: pour_full27!0; goto q; 

:: pour_full28!0; goto q; 

:: pour_full29!0; goto q; 

:: pour_full30!0; goto q; 

fi;
}

active proctype Sink() { 

q: if
:: pour_empty1?0; goto q; 

:: pour_empty2?0; goto q; 

:: pour_empty3?0; goto q; 

:: pour_empty4?0; goto q; 

:: pour_empty5?0; goto q; 

:: pour_empty6?0; goto q; 

:: pour_empty7?0; goto q; 

:: pour_empty8?0; goto q; 

:: pour_empty9?0; goto q; 

:: pour_empty10?0; goto q; 

:: pour_empty11?0; goto q; 

:: pour_empty12?0; goto q; 

:: pour_empty13?0; goto q; 

:: pour_empty14?0; goto q; 

:: pour_empty15?0; goto q; 

:: pour_empty16?0; goto q; 

:: pour_empty17?0; goto q; 

:: pour_empty18?0; goto q; 

:: pour_empty19?0; goto q; 

:: pour_empty20?0; goto q; 

:: pour_empty21?0; goto q; 

:: pour_empty22?0; goto q; 

:: pour_empty23?0; goto q; 

:: pour_empty24?0; goto q; 

:: pour_empty25?0; goto q; 

:: pour_empty26?0; goto q; 

:: pour_empty27?0; goto q; 

:: pour_empty28?0; goto q; 

:: pour_empty29?0; goto q; 

:: pour_empty30?0; goto q; 

fi;
}

