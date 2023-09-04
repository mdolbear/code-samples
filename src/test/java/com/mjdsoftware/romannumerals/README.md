Numerals
I = 1
V = 5
X = 10
L = 50
C = 100
D = 500
M = 1,000


Standard Rule Overview

• Numerals are generally written from large numeral to small in an additive fashion (i.e.  ‘MDL’ would be 1,550, but ‘LDM’ is an invalid roman numeral) except when the subtractive feature is in use (see next rule)

• There is a subtractive feature to decrement a value from a larger value to simplify numerals (i.e.  ‘IV’ is 4 because 1 is subtracted from 5.  ‘CD’ would be 400).  This only happens in pairs where the first numeral is the subtractor from the second numeral.  You could not have a valid roman numeral like ‘ICD’.  You could have combinations of subtractors in a roman numeral like ‘CDIV’ which would be 404

• You cannot have the same numeral repeated if it adds up to the next higher numeral (i.e. ‘VV’ is invalid because ‘X’ is the representation for 10)


Problem Statement

Create a roman numeral converter.  Given a roman numeral, convert it to an integer.  Start with simple cases and work toward more difficult numerals to handle.


XL = 40

X -> L -> V -> I -> I -> I= 48


1, 2, 3, 8, 58, 48


CMDXLIV = C -> M -> D -> X -> L -> I -> V = 1444  V-> I -> I

5, 4, 54, 44, 544, 1544, 1444