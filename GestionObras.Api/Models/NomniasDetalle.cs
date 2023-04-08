using System.ComponentModel.DataAnnotations;

namespace GestionObras.Api.Models
{
    public class NominasDetalle
    {
        [Key]
        public int Id { get; set; }
        public int NominaId { get; set; }
        
        [DataType(DataType.Date)] 
        public string Fecha { get; set; }
        public string? TipoTrabajo { get; set; }
        public double Cantidad { get; set; }
        public double Precio { get; set; }
        public int ProyectoId { get; set; }
        public int PersonaId { get; set; }
        public double Balance { get; set; }
        public Nominas nominas { get; set; } = new Nominas();
        public Personas personas { get; set; } = new Personas();
        public TiposTrabajos tiposTrabajos { get; set; } = new TiposTrabajos();
        public Proyectos proyectos { get; set; } = new Proyectos();

        public NominasDetalle(int proyectoId, int id, int nominaId, string fecha, string tipoTrabajo, double cantidad, double precio, int personaId, double balance)
        {
            Id = id;
            NominaId = nominaId;
            ProyectoId = proyectoId;
            Fecha = fecha;
            TipoTrabajo = tipoTrabajo;
            Cantidad = cantidad;
            Precio = precio;
            PersonaId = personaId;
            Balance = balance;
        }
    }
}