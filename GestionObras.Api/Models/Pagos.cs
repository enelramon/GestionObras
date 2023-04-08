using System.ComponentModel.DataAnnotations;

namespace GestionObras.Api.Models
{
    public class Pagos
    {
        [Key]
        public int PagoId { get; set; }
        public string? Fecha { get; set; }
        public string? PersonaId { get; set; }
        public int ProyectoId { get; set; }
        public double Monto { get; set; } = 0.0;
        public int AdelantoId { get; set; }
        public double Total { get; set; } = 0.0;
    }
}