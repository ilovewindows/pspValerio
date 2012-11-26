package bufferCircular;

public class Productor implements Runnable{
	
	private Buffer buffer;

	
	public Productor(Buffer buffer) {
		
		this.buffer = buffer;
	}


	public void run() {
		while (true) {
			try {
				Thread.sleep(1000);
				int random = (int) (Math.random() * 100);
				buffer.set(random);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
