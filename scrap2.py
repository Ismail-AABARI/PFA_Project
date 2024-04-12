import requests
from bs4 import BeautifulSoup
import csv

# Définir l'URL de la page à partir de laquelle vous souhaitez extraire les données
url = "http://www.liscomputers.com/backaroo/Suppliers/MS_61.html"

# Envoyer une requête GET à l'URL pour obtenir le contenu HTML de la page
response = requests.get(url)

# Vérifier si la requête a réussi (code d'état 200)
if response.status_code == 200:
    # Analyser le contenu HTML de la page
    soup = BeautifulSoup(response.content, 'html.parser')
    
    # Trouver toutes les balises <td> avec la classe "ttc"
    ttc_elements = soup.find_all('td', class_='ttc')
    
    # Définir le nom du fichier CSV de sortie
    output_file = "Supplier.csv"
    
    # Ouvrir le fichier CSV en mode écriture
    with open(output_file, "w", newline="", encoding="utf-8") as csvfile:
        # Créer un objet writer
        writer = csv.writer(csvfile)
        
        # Écrire l'en-tête de colonne dans le fichier CSV
        writer.writerow(["Company Name", "Contact Name", "Contact Title", "Address", "Phone"])
        
        # Initialiser une liste pour stocker les données de chaque entrée
        entry_data = []
        
        # Parcourir tous les éléments <td> avec la classe "ttc"
        for i, element in enumerate(ttc_elements):
            # Ajouter le texte de l'élément à la liste des données de l'entrée
            entry_data.append(element.text.strip())
            
            # Si nous avons ajouté toutes les données d'une entrée
            if (i + 1) % 5 == 0:
                # Écrire les données dans une ligne du fichier CSV
                writer.writerow(entry_data)
                # Réinitialiser la liste des données pour la prochaine entrée
                entry_data = []
    
    print(f"Données insérées avec succès dans le fichier : {output_file}")
else:
    print("La requête a échoué. Veuillez vérifier l'URL et réessayer.")
