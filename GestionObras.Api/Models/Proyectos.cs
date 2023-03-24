using System.ComponentModel.DataAnnotations;

namespace GestionObras.Api.Models
{
    public class Proyectos
    {
        [Key]
        public int ProyectoId { get; set; }

         public string? Descripcion { get; set; }
    }
}
