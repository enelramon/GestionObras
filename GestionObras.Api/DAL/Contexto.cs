using GestionObras.Api.Models;
using Microsoft.EntityFrameworkCore;

namespace GestionObras.Api.DAL
{
    public class Contexto : DbContext
    {
        public Contexto(DbContextOptions<Contexto> options) : base(options)
        {

        }

        public DbSet<Adelantos> Adelantos => Set<Adelantos>();
        public DbSet<Nominas> Nominas => Set<Nominas>();
        public DbSet<Pagos> Pagos => Set<Pagos>();
        public DbSet<Personas> Personas => Set<Personas>();
        public DbSet<Proyectos> Proyectos => Set<Proyectos>();
        public DbSet<TiposTrabajos> TiposTrabajos => Set<TiposTrabajos>();

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            modelBuilder.Entity<Proyectos>().HasData(
                new Proyectos
                {
                    ProyectoId = 1,
                    Descripcion = "Casa de Enel"
                },
                new Proyectos
                {
                    ProyectoId = 2,
                    Descripcion = "Casa jose"
                }
            );
        
            modelBuilder.Entity<TiposTrabajos>().HasData(
                new TiposTrabajos
                {
                    TipoTrabajoId = 1,
                    descripcion = "Carpinteria x dia",
                    precio = 2000
                },
                new TiposTrabajos
                {
                    TipoTrabajoId = 2,
                    descripcion = "Ayudante Carpintero",
                    precio = 1000
                }
            );
        }
    }
}
