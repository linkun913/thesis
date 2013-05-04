package com.example.myapp;

public class QuestionData {
	private String question;
	private int id;
	private float rawRate=(float) 5.0;
	private double privateRate=(double)rawRate+gaussianrvGen(Data.privacyLevel);
	
	public QuestionData() {

	}

	public QuestionData(String question, int id) {
		this.question = question;
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public int getID() {
		return id;
	}

	public float getRawRate() {
		return rawRate;
	}
	
	public double getPrivateRate() {
		return privateRate;
	}
	
	public void setRawRate(float rawRate) {
		this.rawRate = rawRate;
		setPrivateRate();
	}

	private void setPrivateRate() {
		privateRate = (double)rawRate + gaussianrvGen(Data.privacyLevel);
	}

	private double gaussianrvGen(int privacyLevel) {
		double uniformRv_1, uniformRv_2, radiusSquare, gaussianRv, x;
		do {
			uniformRv_1 = Math.random();
			uniformRv_2 = Math.random();
			uniformRv_1 = 2 * uniformRv_1 - 1;
			uniformRv_2 = 2 * uniformRv_2 - 1;
			radiusSquare = uniformRv_1 * uniformRv_1 + uniformRv_2
					* uniformRv_2;
		} while (radiusSquare >= 1 || radiusSquare == 0);
		gaussianRv = Math.sqrt(-2 * Math.log(radiusSquare) / radiusSquare);
		x = gaussianRv * uniformRv_1;
		switch (privacyLevel) {
		case 0:
			return 0.0;
		case 1:
			return (x * 1.0);
		case 2:
			return (x * 2.0);
		case 3:
			return (x * 4.0);
		default:
			return (0);
		}
	}
}
