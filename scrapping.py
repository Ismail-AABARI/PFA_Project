import requests
from bs4 import BeautifulSoup
import csv

# URL de la page Web à partir de laquelle vous souhaitez extraire les données
url = "http://www.liscomputers.com/backaroo/Customers/MS_58.html"

# Obtenir le contenu HTML de la page
response = requests.get(url)
html_content = response.text

# Analyser le contenu HTML avec BeautifulSoup
soup = BeautifulSoup(html_content, "html.parser")

# Extraire tous les éléments avec la classe "ttc"
elements_with_class = soup.find_all("td", class_="ttc")

# Diviser les éléments en listes par groupe de colonnes
columns = 10  # Nombre de colonnes
data = [elements_with_class[i:i+columns] for i in range(0, len(elements_with_class), columns)]

# Écrire les données extraites dans un fichier CSV
with open("output.csv", "w", newline="", encoding="utf-8") as csvfile:
    writer = csv.writer(csvfile)
    # Écrire les en-têtes de colonnes
    writer.writerow(["Customer ID", "Company Name", "Contact Name", "Email Address", "Contact Title",
                     "Address", "Phone", "Fax", "Orders", "Product"])
    # Écrire les données pour chaque ligne
    for row in data:
        # Récupérer le texte de chaque élément de la ligne
        row_data = [column.get_text(strip=True) for column in row]
        # Fusionner les données de l'adresse en une seule chaîne
        address = " ".join(row_data[5:8])
        # Supprimer les données de l'adresse de la liste
        del row_data[5:8]
        # Insérer les données de l'adresse dans la liste après "Address"
        row_data.insert(5, address)
        # Écrire la ligne dans le fichier CSV
        writer.writerow(row_data)
