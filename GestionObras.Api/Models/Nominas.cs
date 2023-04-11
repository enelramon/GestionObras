using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

#nullable disable

namespace GestionObras.Api.Models
{
    public class Nominas
    {
        [Key]
        public int NominaId { get; set; }
        public string Fecha { get; set; }
        public int PersonaId { get; set; }
        public int ProyectoId { get; set; }
        public double Total { get; set; }
        public string Estado { get; set; }
    }
}
