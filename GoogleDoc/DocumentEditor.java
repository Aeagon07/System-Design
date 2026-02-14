
public interface DocumentElement{
    public abstract String render();
}

class TextElement implements DocumentElement{
    private String text;

    public TextElement(String text){
        this.text = text;
    }

    @overide
    public String render(){
        return text;
    }
}

class ImageElement implements DocumentElement{
    private String path;

    public ImageElement(String path){
        this.path = path;
    }

    @Override
    public String render(){
        return "[Image: " + path + "]";
    }
} 

// Open-Close Principle following

// class NewLine implements DocumentElement{
//     @override
//     public String render(){
//         return "\n";
//     }
// }

class Document{
    private List<DocumentElement> docEle = new ArrayList<>();

    public void addElement(DocumentElement element){
        docEle.add(element);
    }

    public String render(){
        StringBuilder res = new StringBuilder();

        for(DocumentElement element: docEle){
            res.append(element.render());
        }

        return res.toString();
    }
}

interface Persistence{
    void string(String data);
}

class FileStorage implements Persistence{
    @Override

    public void save(String data){
        try{
            FileWriter writer = new FileWriter("document.txt");
            writer.write(renderDocument());
            writer.close();
            System.out.println("Document saved to document.txt");
        }catch (IOException e){
            System.out.println("Error: Unable to open file for writing.");
        }
    }
}

class SaveToDB implements Persistence{
    @override
    public void save(String data){
        // save to DB
    }
}

class DocumentEditor {
    private Document document;
    private Persistence storage;
    private String renderedDocument = "";

    public DocumentEditor(Document document, Persistence storage) {
        this.document = document;
        this.storage = storage;
    }

    public void addText(String text) {
        document.addElement(new TextElement(text));
    }

    public void addImage(String imagePath) {
        document.addElement(new ImageElement(imagePath));
    }

    //  Adds a new line to the document.
    // public void addNewLine() {
    //     document.addElement(new NewLineElement());
    // }

    // // Adds a tab space to the document.
    // public void addTabSpace() {
    //     document.addElement(new TabSpaceElement());
    // }

    public String renderDocument() {
        if (renderedDocument.isEmpty()) {
            renderedDocument = document.render();
        }
        return renderedDocument;
    }

    public void saveDocument() {
        storage.save(renderDocument());
    }
}

public class DocumentEditorClient {
    public static void main(String[] args) {
        Document document = new Document();
        Persistence persistence = new FileStorage();

        DocumentEditor editor = new DocumentEditor(document, persistence);

        editor.addText("Hello, world!");

        editor.addText("This is a real-world document editor example.");

        editor.addText("Indented text after a tab space.");

        editor.addImage("picture.jpg");

        System.out.println(editor.renderDocument());

        editor.saveDocument();
    }
}