import site
import numpy as np
import polyglot as poly

def sortArray(arr):
    sortedArr = np.sort(arr)
    return sortedArr

def heartAnalysis():
    heartData = np.genfromtxt('heart.csv', delimiter=',')
    peopleWhoHasChestPainTypeTwo = np.where((heartData[:,2]>1) & (heartData[:,2]<3) & ((heartData[:,0])>60))
    dataOfPeopleWith3ChestPain = heartData[np.where(heartData[:,2]>2)]
    averageAgeofPeopleWith3ChestPain = np.average(dataOfPeopleWith3ChestPain[:,0])
    # Average age of people who are getting level 3 and greater chest pain
    return str(averageAgeofPeopleWith3ChestPain)

poly.export_value("sortArray", sortArray)
poly.export_value("heartAnalysis", heartAnalysis)
