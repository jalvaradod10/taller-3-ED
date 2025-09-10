

def leer_csv(nombre_archivo):
    "Lee un CSV y devuelve lista de listas (filas)"
    with open(nombre_archivo, "r", encoding="utf-8") as f:
        lineas = f.read().strip().split("\n")
    datos = [linea.split(",") for linea in lineas]
    return datos

def escribir_csv(nombre_archivo, encabezado, filas):
    """Escribe un CSV con encabezado y filas."""
    with open(nombre_archivo, "w", encoding="utf-8") as f:
        f.write(",".join(encabezado) + "\n")
        for fila in filas:
            f.write(",".join(str(x) for x in fila) + "\n")

def selection_sort(lista, key_func=lambda x: x, reverse=False):
    """Ordena lista con Selection Sort usando clave key_func."""
    n = len(lista)
    for i in range(n):
        idx_extremo = i
        for j in range(i+1, n):
            if reverse:
                if key_func(lista[j]) > key_func(lista[idx_extremo]):
                    idx_extremo = j
            else:
                if key_func(lista[j]) < key_func(lista[idx_extremo]):
                    idx_extremo = j
        lista[i], lista[idx_extremo] = lista[idx_extremo], lista[i]
    return lista

# ---------------- OPCIÓN 1 ----------------
def ver_libros_ordenados():
    datos = leer_csv("libros.csv")
    encabezado, filas = datos[0], datos[1:]
    filas = selection_sort(filas, key_func=lambda x: int(x[4]))
    print("\n--- Libros ordenados por año asc ---")
    for fila in filas:
        print(f"{fila[1]}, {fila[2]}, {fila[4]}, stock={fila[5]}")

# ---------------- OPCIÓN 2 ----------------
def agregar_usuario():
    datos = leer_csv("usuarios.csv")
    encabezado, filas = datos[0], datos[1:]
    nuevo_id = max(int(f[0]) for f in filas) + 1
    nombre = input("Nombre del usuario: ")
    email = input("Email del usuario: ")
    filas.append([str(nuevo_id), nombre, email])
    escribir_csv("usuarios.csv", encabezado, filas)
    print(f"Usuario agregado con id={nuevo_id}.")

def calcular_total_prestamos():
    libros = leer_csv("libros.csv")
    prestamos = leer_csv("prestamos.csv")
    encabezado_libros, filas_libros = libros[0], libros[1:]
    encabezado_prestamos, filas_prestamos = prestamos[0], prestamos[1:]

    totales = {}
    for fila in filas_prestamos:
        libro_id, cantidad = fila[2], int(fila[3])
        totales[libro_id] = totales.get(libro_id, 0) + cantidad

    resultados = []
    for fila in filas_libros:
        libro_id, titulo = fila[0], fila[1]
        if libro_id in totales:
            resultados.append([libro_id, titulo, totales[libro_id]])

    resultados = selection_sort(resultados, key_func=lambda x: x[2], reverse=True)

    print("\n--- Total de préstamos por libro (mayor a menor) ---")
    for fila in resultados:
        print(f"{fila[1]} , total_prestamos: {fila[2]}")

    escribir_csv("total_prestamos.csv", ["libro_id", "titulo", "total_prestamos"], resultados)
    print("Archivo total_prestamos.csv generado correctamente.")

def ver_usuarios_con_prestamos():
    usuarios = leer_csv("usuarios.csv")
    prestamos = leer_csv("prestamos.csv")
    encabezado_u, filas_u = usuarios[0], usuarios[1:]
    encabezado_p, filas_p = prestamos[0], prestamos[1:]

    ids_con_prestamo = set(f[1] for f in filas_p)
    usuarios_filtrados = [u for u in filas_u if u[0] in ids_con_prestamo]

    usuarios_filtrados = selection_sort(usuarios_filtrados, key_func=lambda x: x[1])

    print("\n--- Usuarios con préstamos Asc ---")
    for u in usuarios_filtrados:
        print(f"{u[0]} , {u[1]} , {u[2]}")

def menu():
    while True:
        print("\n===== MENÚ BIBLIOTECA =====")
        print("1. Ver libros ordenados por año")
        print("2. Agregar un nuevo usuario")
        print("3. Calcular total de préstamos por libro")
        print("4. Ver usuarios que han realizado préstamos")
        print("5. Salir")
        opcion = input("Seleccione una opción: ")

        if opcion == "1":
            ver_libros_ordenados()
        elif opcion == "2":
            agregar_usuario()
        elif opcion == "3":
            calcular_total_prestamos()
        elif opcion == "4":
            ver_usuarios_con_prestamos()
        elif opcion == "5":
            print("Saliendo...")
            break
        else:
            print("Opción inválida.")

if __name__ == "__main__":
    menu()