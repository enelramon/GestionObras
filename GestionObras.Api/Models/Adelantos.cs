using System.ComponentModel.DataAnnotations;

namespace GestionObras.Api.Models
{
    public class Adelantos
    {
        [Key]
        public int AdelantoId { get; set; }

        [DataType(DataType.Date)] 
        public DateTime Fecha { get; set; } = DateTime.Now;
        public int PersonaId { get; set; }
        public double Monto { get; set; }
        public double Balance { get; set; }
    }
}