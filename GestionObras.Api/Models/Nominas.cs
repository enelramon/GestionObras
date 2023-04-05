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
        public int ProyectoId { get; set; }
        public double Total { get; set; }
        public string Estado { get; set; }

        [ForeignKey("ProyectoId")]
        public virtual Proyectos proyectos { get; set; }

        [ForeignKey("NominaId")]
        public virtual List<NominasDetalle> nominasDetalle { get; set; }

        public Nominas()
        {
            NominaId = 0;
            Fecha = string.Empty;
            ProyectoId = 0;
            Total = 0;
            Estado = string.Empty;

            nominasDetalle = new List<NominasDetalle>();
        }
    }
}
