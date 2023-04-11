using System.ComponentModel.DataAnnotations;

namespace GestionObras.Api.Models
{
    public class TiposTrabajos
    {
        [Key]
        public int TipoTrabajoId { get; set; }
        public string? descripcion { get; set; }
        public double precio { get; set; }
    }
}
