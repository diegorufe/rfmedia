package com.RFCore.utils.bytes;

public class TestBytes {
	public static void main(String[] args) {
		byte a = (byte) 0x80;
		int b = obtenerValorPositivoByte(a);
		System.out.println(Integer.toBinaryString(b));
		int resultado = 0b10000001;
		resultado = cambiarBit(resultado, 5, false);
		System.out.println(Integer.toBinaryString(resultado));
		resultado = cambiarBit(resultado, 6, false);
		System.out.println(Integer.toBinaryString(resultado));
		resultado = cambiarBit(resultado, 4, true);
		System.out.println(Integer.toBinaryString(resultado));
		resultado = cambiarBit(resultado, 3, false);
		System.out.println(Integer.toBinaryString(resultado));
		resultado = cambiarBit(resultado, 2, true);
		System.out.println(Integer.toBinaryString(resultado));
		resultado = cambiarBit(resultado, 1, true);
		System.out.println(Integer.toBinaryString(resultado));
		resultado = cambiarBit(resultado, 0, false);
		System.out.println(Integer.toBinaryString(resultado));
		System.out.println(Integer.toBinaryString(getBit(resultado, 6)));
		System.out.println(Integer.toBinaryString(getBit(resultado, 2)));
		System.out.println(obtenerValorPosicionByte(hayBit(resultado, 6), 2));
	}

	public static int getBit(int value, int position) {
		return (value >> position) & 1;
	}
	
	public static boolean hayBit(int value, int position) {
		return ((value >> position) & 1) == 1;
	}

	// Returns modified n.
	public static int cambiarBit(int numero, int posicion, boolean esUno) {
		int mask = 1 << posicion;
		int value = esUno ? 1 : 0;
		return ((numero & ~mask) | ((value << posicion) & mask));
	}
	
	public static int obtenerValorPositivoByte(byte value) {
		return value & 0xFF;
	}
	
	public static int obtenerValorPosicionByte(boolean esUno, int posicion) {
		return (int) (esUno ? Math.pow(2, posicion) : 0);
	}
}
