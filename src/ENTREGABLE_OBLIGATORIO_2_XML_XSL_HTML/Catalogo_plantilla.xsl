<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
                xmlns:xsl="http://www.w3.org/1999/XSL/Transform">

  <xsl:template match="/">
    <html>
      <head>
        <title>Lista de Videojuegos</title>
        <style>
          table {
            border-collapse: collapse;
            width: 100%;
          }
          th, td {
            border: 1px solid black;
            padding: 8px;
            text-align: left;
          }
          th {
            background-color: #f2f2f2;
          }
          tr:nth-child(even) {
            background-color: #f9f9f9;
          }
        </style>
      </head>
      <body>
        <h2>Lista de Videojuegos</h2>
        <table>
          <tr>
            <th>ID</th>
            <th>Título</th>
            <th>Año</th>
            <th>Compañía</th>
            <th>Consola</th>
            <th>Puntuación</th>
            <th>Recomendación</th>
          </tr>
          <!-- Iterar sobre cada videojuego -->
          <xsl:for-each select="videojuegos/videojuego">
            <tr>
              <td><xsl:value-of select="@id"/></td>
              <td><xsl:value-of select="titulo"/></td>
              <td><xsl:value-of select="anio"/></td>
              <td><xsl:value-of select="compania"/></td>
              <td><xsl:value-of select="consola"/></td>
              <td><xsl:value-of select="puntuacion"/></td>
               <td><xsl:value-of select="recomendacion"/></td>
            </tr>
          </xsl:for-each>
        </table>
      </body>
    </html>
  </xsl:template>

</xsl:stylesheet>