using GestionObras.Api.Models;
using Microsoft.EntityFrameworkCore;

namespace GestionObras.Api.DAL
{
    public class Contexto:DbContext
    {
        public Contexto(DbContextOptions<Contexto> options): base(options) 
        {

        }

        public DbSet<Personas> Personas => Set<Personas>();
        public DbSet<Proyectos> Proyectos => Set<Proyectos>();
        public DbSet<TiposTrabajos> TiposTrabajos => Set<TiposTrabajos>();

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);
            //modelBuilder.Entity<Proyectos>().HasData(new Proyectos { });
            modelBuilder.Entity<Personas>().HasData(new Personas {
                PersonaId = 1,
                Nombres = "Manuel",
                Telefono = "829-811-4569"
             });
            
        }
    }
}
