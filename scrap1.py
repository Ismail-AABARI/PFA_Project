import requests
from bs4 import BeautifulSoup
import csv

# Définir l'URL de la page à partir de laquelle vous souhaitez extraire les données
url = "http://www.liscomputers.com/backaroo/Products/MS_56.html"

# Envoyer une requête GET à l'URL pour obtenir le contenu HTML de la page
response = requests.get(url)

# Vérifier si la requête a réussi (code d'état 200)
if response.status_code == 200:
    # Analyser le contenu HTML de la page
    soup = BeautifulSoup(response.content, 'html.parser')
    
    # Trouver toutes les balises <td> avec la classe "ttc"
    ttc_elements = soup.find_all('td', class_='ttc')
    
    # Définir le nom du fichier CSV de sortie
    output_file = "Product.csv"
    
    # Ouvrir le fichier CSV en mode écriture
    with open(output_file, "w", newline="", encoding="utf-8") as csvfile:
        # Créer un objet writer
        writer = csv.writer(csvfile)
        
        # Écrire les en-têtes de colonnes dans le fichier CSV
        writer.writerow(["Product Name", "Supplier", "Category", "Quantity Per Unit", "Unit Price", 
                         "Units In Stock", "Units On Order", "Reorder Level", "Discontinued", "Total Sales"])
        
        # Parcourir tous les éléments <td> avec la classe "ttc"
        for i in range(0, len(ttc_elements), 10):
            # Extraire les données pour chaque produit
            product_data = [element.text.strip() for element in ttc_elements[i:i+10]]
            # Écrire les données dans une ligne du fichier CSV
            writer.writerow(product_data)
    
