
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;


public class lyTrans {

	int intervalSize;
	JFrame main = new JFrame("Lilypond Transpose");
	JTextArea one = new JTextArea(10,20);
	JTextArea two = new JTextArea(10,20);
	JButton go = new JButton("Transpose!");
	String[] options = { "+6","+5","+4","+3","+2","+1","0","-1","-2","-3","-4","-5","-6"};
	JComboBox<String> pitches = new JComboBox<>(options);
	
	public lyTrans(){
		go.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				two.setText(transpose(one.getText(),Integer.parseInt((String) pitches.getSelectedItem())));	
			}});
		Box b = Box.createVerticalBox();
		Box text = Box.createHorizontalBox();
		Box menuArea = Box.createHorizontalBox();
		Box middle = Box.createVerticalBox();
		middle.add(new JLabel("←old  new→"));
		pitches.setSelectedIndex(6);
		menuArea.add(go);
		menuArea.add(pitches);
		text.add(one);
		text.add(middle);
		text.add(two);
		b.add(text);
		b.add(menuArea);
		one.setLineWrap(true);
		two.setLineWrap(true);
		main.add(b);
		main.setSize(500,300);
		main.setVisible(true);
		
	}
	
	private String transpose(String thought, int interval){
		ArrayList<Integer> pos = new ArrayList<>();
		for(int i = 0; i<thought.length(); i++){
			if(i+3>thought.length()-1){
				if(stringToInt(thought.substring(i))!=-1){
					pos.add(i);
				}
			}else{
				if(stringToInt(thought.substring(i,i+3))!=-1){
					pos.add(i);
				}
			}
		}
		
		for(int i = 0; i<pos.size()-1; i++){
			if(pos.get(i+1)-1==pos.get(i)){
				pos.remove(i+1);
			}
		}
		String ret = thought;
		
		for(int i = pos.size()-1; i>=0; i--){
			int posi = pos.get(i);
			String note = ret.substring(posi);
			if(note.contains(" ")){
				note = note.substring(0,note.indexOf(" "));
			}
			for(int asdf = 0; asdf<note.length();asdf++){
				//65 - 90 or 97 to 122
				char m = note.charAt(asdf);
				if(m<65||m>122||(m>90&&m<97)){
					note = note.substring(0,asdf);
					break;
				}
			}
			int pitch = stringToInt(note);
			pitch = pitch+interval;
			if(pitch>12){ pitch = pitch- 12;}
			if(pitch<1){ pitch = pitch+ 12;}
			String firstHalf = ret.substring(0,posi);
			String secondHalf = ret.substring(posi+note.length());
			ret = firstHalf+intToString(pitch,true)+secondHalf;
		}
		
		return ret;
	}
	
	private String intToString(int pitch,boolean perferSharp){
		if(pitch == 1) { return "c"; }
		if(pitch == 2 && perferSharp) { return "cis"; }
		if(pitch == 2) { return "des"; }
		if(pitch == 3) { return "d"; }
		if(pitch == 4 && perferSharp) { return "dis"; }
		if(pitch == 4 ) { return "es"; }
		if(pitch == 5 ) { return "e"; }
		//if(pitch == 5 ) { return "fes"; }
		if(pitch == 6 ) { return "f"; }
		//if(pitch == 6 ) { return "eis"; }
		if(pitch == 7 && perferSharp) { return "fis"; }
		if(pitch == 7 ) { return "ges"; }
		if(pitch == 8 ) { return "g"; }
		if(pitch == 9 && perferSharp) { return "gis"; }
		if(pitch == 9 ) { return "as"; }
		if(pitch == 10){ return "a"; }
		if(pitch == 11&& perferSharp){ return "ais"; }
		if(pitch == 11){ return "bes"; }
		if(pitch == 12){ return "b"; }
		//if(pitch == 12){ return "bes"; }
		//if(pitch == 1 ) { return "bis"; }
		return "";
	}
	
	private int stringToInt(String letter){
		if(letter.startsWith("ces")){return 12 ;}
		if(letter.startsWith("bis")){return 1 ;}
		if(letter.startsWith("bes")){return 11 ;}
		if(letter.startsWith("gis")){return 9 ;}
		if(letter.startsWith("eis")){return 6 ;}
		if(letter.startsWith("fis")){return 7 ;}
		if(letter.startsWith("ges")){return 7 ;}
		if(letter.startsWith("fes")){return 5 ;}
		if(letter.startsWith("dis")){return 4 ;}
		if(letter.startsWith("dis")){return 4 ;}
		if(letter.startsWith("cis")){return 2 ;}
		if(letter.startsWith("des")){return 2 ;}
		if(letter.startsWith("es")){return 4 ;}
		if(letter.startsWith("as")){return 9 ;}
		if(letter.startsWith("ai")){return 11 ;}
		if(letter.startsWith("c")){return 1 ;}
		if(letter.startsWith("d")){return 3 ;}
		if(letter.startsWith("e")){return 5 ;}
		if(letter.startsWith("f")){return 6 ;}
		if(letter.startsWith("g")){return 8 ;}
		if(letter.startsWith("a")){return 10 ;}
		if(letter.startsWith("b")){return 12 ;}
		return -1;
	}
}
