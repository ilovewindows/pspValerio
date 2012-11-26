package bufferCircular;

import java.util.*;

public class Buffer {

	
	private int[] num;
	private int g = 0;
	private int s = 0;
	private int n;
	

	

	public Buffer(int n) {
		this.n = n;
		num = new int[n];
	}
	
	public Buffer(){
		this(10);
	}

	public void clear(){
		s=g=s=0;
	}
	
	public synchronized void set(int valor){
		if(! complete()){
		num[s] = valor;
		s++;
		if(s == num.length)
			s = 0;
		n++;
		notifyAll();
	}else{
		try {
			wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		}
	
	public synchronized int get() {
		while (true) {
			if (!empty()) {
				int aux = num[g];
				g++;
				if (g == num.length) 
					g = 0;
				n--;
				return aux;
				}else{
					try {
						wait();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
				}
				
			}
		}
	}

	public boolean empty(){
		return n == 0;
	}
	
	public boolean complete(){
		return n == num.length;
	}
	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getS() {
		return s;
	}

	public void setS(int s) {
		this.s = s;
	}

	public int getN() {
		return n;
	}

	public void setN(int n) {
		this.n = n;
	}

	public static void main(String[] args) {
		Buffer buff = new Buffer();
		Thread pr = new Thread(new Productor(buff));
		Thread cs = new Thread(new Consumidor(buff));
		pr.start();
		cs.start();
	}

}
