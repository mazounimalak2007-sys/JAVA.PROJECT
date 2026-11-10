package tp_fichiers;

public class Exercice4 {
	public static void sauvegarder(ArrayList<Produit> liste, String nom)    {
        try (ObjectOutputStream oos = new ObjectOutputStream (new FileOutputStream(nom)))    {
            oos.writeObject(liste);
            System.out.println(liste.size() + " produits sauvegardés");
        } 
        
        catch (IOException e)   {
            System.out.println("Erreur lors de la sauvegarde : " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked");
    public static ArrayList<Produit> charger(String nom) {
        File file = new File(nom);
          if (!file.exists())    {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nom)))    {
            return (ArrayList<Produit>) ois.readObject();
        } 
        catch (FileNotFoundException e) {
            System.out.println("Fichier introuvable.");
        } 
        catch (IOException e)    {
            System.out.println("Erreur I/O : " + e.getMessage());
        } catch (ClassNotFoundException e)    {
            System.out.println("Classe introuvable : " + e.getMessage());
        }
        return new ArrayListt<>();
    }

    public static void main(String[] args) {
        String fichierStock = "tp_fichiers/stock.date";
        ArrayList<Produit> stock = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader("tp_fichiers/produits.txt"))) {
            String ligne;
            while ((ligne = br.readLine()) != null)    {
                String[] parts = ligne.split(";");
                if (parts.length == 3) {
                    int num = Integer.parseInt(parts[0].trim());
                    String des = parts[1].trim();
                    float prix = Float.parseFloat(parts[2].trim());
                    stock.add(new Produit(num, des, prix));
                }
            }
        } 
        catch (IOException e) {
            System.out.println("Erreur de lecture du fichier texte : " + e.getMessage());
        }

        sauvegarder(stock, fichierStock);

        stock = charger(fichierStock);

        stock.add(new Produit(6, "Webcam", 350.0f));

        sauvegarder(stock, fichierStock);

        stock = charger(fichierStock);

        for (Produit p : stock) 
        {
            System.out.println(p);
            
           
        }
    }
}
