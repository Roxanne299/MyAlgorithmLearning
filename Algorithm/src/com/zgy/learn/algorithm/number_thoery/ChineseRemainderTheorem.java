package com.zgy.learn.algorithm.number_thoery;

//中国剩余定理
//m1,m2,m3,...,mk 两两互质
//x = a1 (mod m1)
//x = a2 (mod m2)
//x = a3 (mod m3)
//...
//x = ak (mod mk)
//M = m1*m2*m3*...*mk
//Mi = M/mi (Mi 和 mi 互質)  Mi^-1表示Mi模m的逆
//x = a1*M1*M1^-1 + a2*M2*M2^-1 + a3*M3*M3^-1 + ... + ak*Mk*Mk^-1
//当mod m1的时候，M1*M1^-1 = 1 其余的Mi包含m1 所以其余的都是m1的倍数
public class ChineseRemainderTheorem {
}
