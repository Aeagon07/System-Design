interface IReports {

    String getJsonData(String data);
}

// Adaptee:- Third Party Class XML Provider
class XmlDataProvider {
    // Expect data in "name:id" format (e.g. "Alice:42")

    String getXmlData(String data) {
        int sep = data.indexOf(':');
        String name = data.substring(0, sep);
        String id   = data.substring(sep + 1);

        return "<user>"
                + "<name>" + name + "</name>"
                + "<id>"   + id   + "</id>"
                + "</user>";
    }
}

// This is your Adapter => this is your connector to XML -> JSON
class XmlDataProviderAdapter implements IReports {
    private XmlDataProvider xmlProvider;
    public XmlDataProviderAdapter(XmlDataProvider provider) {
        this.xmlProvider = provider;
    }

    public String getJsonData(String data) {
        String xml = xmlProvider.getXmlData(data);

        int startName = xml.indexOf("<name>") + 6;
        int endName   = xml.indexOf("</name>");
        String name   = xml.substring(startName, endName);

        int startId = xml.indexOf("<id>") + 4;
        int endId   = xml.indexOf("</id>");
        String id    = xml.substring(startId, endId);

        return "{\"name\":\"" + name + "\", \"id\":" + id + "}";
    }
}

// This is client class that interact with iReport 
class Client {
    public void getReport(IReports report, String rawData) {
        System.out.println("Processed JSON: " + report.getJsonData(rawData));
    }
}

public class AdapterPattern {
    public static void main(String[] args) {
        // 1. Create the adaptee
        XmlDataProvider xmlProv = new XmlDataProvider();

        // 2. Make our adapter
        IReports adapter = new XmlDataProviderAdapter(xmlProv);

        // 3. Give it some raw data
        String rawData = "Alice:42";

        // 4. Client prints the JSON
        Client client = new Client();

        client.getReport(adapter, rawData);
        // → Processed JSON: {"name":"Alice", "id":42}
    }
}