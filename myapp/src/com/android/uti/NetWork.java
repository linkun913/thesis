package com.android.uti;

import java.io.BufferedReader;

import android.os.StrictMode;
import android.util.Log;

import com.example.myapp.*;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;




public class NetWork {
	DatagramSocket clientSocket=null;
	Socket s = null;
	public class Constants {
		public static final int itemID = 3;
		public static final int endOfTheRow = 3;
		public static final int size4 = 4;
		public static final int size200 = 200;

	}
	public void setUDPSend(byte[] send){
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UDPsend(send);
		clientSocket.close();
	}
	public byte[] setUDPReceive(){
		byte[] temp=new byte[128];
		byte[] b=null;
		try {
			clientSocket = new DatagramSocket();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		b =UDPreceive(temp);
		clientSocket.close();
		return b;
	}

	 public byte[] str2bytes(String str){
		return str.getBytes();
	}
	 public String bytes2str(byte[] b1){
		return new String(b1);
	}
	public byte[] concat(byte[] a, byte[] b) {
		int aLength = a.length;
		int bLength = b.length;
		byte[] c = new byte[aLength + bLength];
		for (int i = 0; i < aLength + bLength; i++) {
			if (i < aLength) {
				c[i] = a[i];
			} else {
				c[i] = b[i - aLength];
			}
		}
		return c;
	}
	
	public byte[] UDPreceive(byte[] temp)  {

		DatagramPacket receivePacket;

			receivePacket = new DatagramPacket(temp, temp.length);
			try {
				clientSocket.receive(receivePacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 

		return receivePacket.getData();

	}

	public void UDPsend(byte[] sendData)  {
		InetAddress IPAddress=null;
		try {
			IPAddress = InetAddress.getByAddress(Data.hostBytes);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// sendData = str1.getBytes();
		/* send */
		DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, IPAddress, 31180);

			try {
				clientSocket.send(sendPacket);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	public String http_request(String host, String inputCommand){
		return ("GET http://" + host + inputCommand+ " HTTP/1.0\r\n\r\n");
	}
	
	public void http10TcpSendOnly(String str){
		TCPConn();
		sendStr(str);
		closeTCPSocket();
	}
	public void disable_policy(){
        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
	}
	public int byteToInt(byte[] b) {

		int mask = 0xff;
		int temp = 0;
		int n = 0;
		for (int i = 0; i < 4; i++) {
			n <<= 8;
			temp = b[i] & mask;
			n |= temp;
		}
		return n;
	}

	public String[] http10TcpRecOnly(){
		String[] line;
		TCPConn();
		line=recStr();
		closeTCPSocket();
		return line;
	}
	public String[] http10TcpSendAndRec(String str){
		String[] line;
		TCPConn();
		sendStr(str);
		line=recStr();
		closeTCPSocket();
		return line;
	}
	public String[] http10TcpRecAndSend(String str){
		String[] line;
		TCPConn();
		line=recStr();
		sendStr(str);
		closeTCPSocket();
		return line;
	}

	public Socket TCPConn() {

		try {
			s = new Socket(Data.host, Data.port);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(s!=null){
			Log.e("123","123");
		} else{
			Log.e("321","321");
		}
		return s;
	}

	public void closeTCPSocket() {
		try {
			s.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s=null;
	}
	public void sendStr(String content){
		OutputStream out = null;
		try {
			if(s!=null){
				Log.e("123","123");
			} else{
				Log.e("321","321");
			}
			out = s.getOutputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		PrintWriter outw = new PrintWriter(out, false);
		outw.print(content);
		outw.flush();
	}
	public String[] recStr (){
		List<String>received=new ArrayList <String> ();
		String temp;
		InputStream in = null;
		try {
			in = s.getInputStream();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		InputStreamReader inr = new InputStreamReader(in);
		BufferedReader br = new BufferedReader(inr);

		try {
			while ((temp = br.readLine()) != null) {				
				received.add(temp);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fromList2StrArr(received);
	}
	public void forPrintHexString(byte[] b) {
		for (int i = 0; i < b.length; i++) {
			String hex = Integer.toHexString(b[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			System.out.print(hex.toUpperCase());
		}
		System.out.print("\n");
	}
	public String[] fromList2StrArr(List<String> received){
		String[] line=new String [received.size()];
		line=(String[])received.toArray(line);
		return line;
	}

	public double gaussianrvGen(int privacyLevel) {
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

	public int realLength2D(String[][] line) {
		int i = 0;
		while (line[i][Constants.endOfTheRow] != null) {
			i++;
		}
		return i;
	}

	public int realLength(String[] line) {
		int i = 0;
		while (line[i] != null) {
			i++;
		}
		return i;
	}
	public String[][] surveyData(String[] line) {
		int k = 0;
		int m = 0;
		int n = 0;
		//k = realLength(line);
		String delims = "[{\":}]";

		String[] tokens = line[7].split(delims);

		String[][] questionire1 = new String[Constants.size200][Constants.size4];

		for (k = 0; k < tokens.length; k++) {
			tokens[k] = tokens[k].trim();
		}

		for (k = 1; k < tokens.length; k++) {
			if (tokens[k].length() > 0 && tokens[k].equals(",") == false
					&& tokens[k].equals("]") == false) {
				questionire1[m][n] = tokens[k];
				if (n == Constants.endOfTheRow) {
					n = 0;
					m++;
				} else {
					n++;
				}
			}

		}
		return questionire1;
	}
	public String[] TcpConnection(String inputCommand) throws IOException {
		//Socket s = null;
		String[] line = new String[Constants.size200];
		int i = 0;
		try {

			s = new Socket(Data.host, Data.port);

			OutputStream out = s.getOutputStream();
			PrintWriter outw = new PrintWriter(out, false);
			outw.print("GET http://" + Data.host + inputCommand
					+ " HTTP/1.0\r\n\r\n");
			outw.flush();

			InputStream in = s.getInputStream();
			InputStreamReader inr = new InputStreamReader(in);
			BufferedReader br = new BufferedReader(inr);

			while ((line[i] = br.readLine()) != null) {

				i++;
			}

			s.close();
		} catch (UnknownHostException e) {
		} catch (IOException e) {
		}

		if (s != null) {
			try {
				s.close();
			} catch (IOException ioEx) {
			}
		}
		return line;

	}
	public class B {  
	    private String que1;
	    private Integer id1;  

	    public String getQue() {  
	        return que1;  
	    } 


	    public void setQue(String que1) {  
	        this.que1 = que1;  
	    }

	    public Integer getId() {  
	        return id1;  
	    }  

	    public void setId(Integer id1) {  
	        this.id1 = id1;  
	    }  
	} 


}


