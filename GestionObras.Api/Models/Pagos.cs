using System.ComponentModel.DataAnnotations;

namespace GestionObras.Api.Models
{
    public class Pagos
    {
        [Key]
        public int PagoId { get; set; }
        public DateTime Fecha { get; set; } = DateTime.Now;
        public string? PersonaId { get; set; }
        public double Monto { get; set; } = 0.0;
        public int AdelantoId { get; set; }
        public double Total { get; set; } = 0.0;
    }
}