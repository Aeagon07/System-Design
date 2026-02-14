import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Bad_Design{
    public List<String> documentElements;
    public String renderDoc;

    public Bad_Design(){
        documentElements = new ArrayList<>();
        renderDoc = "";
    }

    // As of now we have only text and images

    public void addText(String text){
        documentElements.add(text);
    }

    public void addImage(String path){
        documentElements.add(path);
    }

    public String renderDocument(){
        if(renderDoc.isEmpty()){
            StringBuilder res = new StringBuilder();
            for(String ele: documentElements){
                if(ele.length() > 4 && (ele.endsWith(".jpg")) || ele.endsWith(".png")){
                    res.append("[Image: ").append(ele).append("]\n");
                }else{
                    res.append(ele).append("\n");
                }
            }
            renderDoc = res.toString();
        }
        return renderDoc;
    }

    public void saveToFile(){
        try{
            FileWriter writer = new FileWriter("document.txt");
            writer.write(renderDocument());
            writer.close();
            System.out.println("Document saved to document.txt");
        }catch(IOException e){
            System.out.println("Error: Unable to open file for writing.");

        }
    }

    public class Client{
        public static void main(String[] args) {
            Bad_Design editor = new Bad_Design();

            editor.addText("Hello, world!");
            editor.addImage("picture.jpg");

            editor.addText("This is document editor");

            System.out.println(editor.renderDocument());

            editor.saveToFile();
        }
    }



}
