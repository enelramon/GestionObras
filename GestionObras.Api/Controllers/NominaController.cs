using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using GestionObras.Api.DAL;
using GestionObras.Api.Models;

namespace GestionObras.Api.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class NominasController : ControllerBase
    {
        private readonly Contexto _context;

        public NominasController(Contexto context)
        {
            _context = context;
        }

        // GET: api/Nominas
        [HttpGet]
        public async Task<ActionResult<IEnumerable<Nominas>>> GetNominas()
        {
            if (_context.Nominas == null)
            {
                return NotFound();
            }
            return await _context.Nominas.ToListAsync();
        }

        // GET: api/Nominas/5
        [HttpGet("{id}")]
        public async Task<ActionResult<Nominas>> GetNominas(int id)
        {
            if (_context.Nominas == null)
            {
                return NotFound();
            }

            var nominas = await _context.Nominas.FindAsync(id);

            if (nominas == null)
            {
                return NotFound();
            }

            return nominas;
        }

        // PUT: api/Nominas/5
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPut("{id}")]
        public async Task<IActionResult> PutNominas(int id, Nominas Nominas)
        {
            if (id != Nominas.NominaId)
            {
                return BadRequest();
            }

            _context.Entry(Nominas).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!NominasExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/Nominas
        // To protect from overposting attacks, see https://go.microsoft.com/fwlink/?linkid=2123754
        [HttpPost]
        public async Task<ActionResult<Nominas>> PostNominas(Nominas nominas)
        {
            if (_context.Nominas == null)
            {
                return Problem("Entity set 'Contexto.Nominas'  is null.");
            }
            _context.Nominas.Add(nominas);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetNominas", new { id = nominas.NominaId }, nominas);
        }

        // DELETE: api/Nominas/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteNominas(int id)
        {
            if (_context.Nominas == null)
            {
                return NotFound();
            }
            var nominas = await _context.Nominas.FindAsync(id);
            if (nominas == null)
            {
                return NotFound();
            }

            _context.Nominas.Remove(nominas);
            await _context.SaveChangesAsync();

            return NoContent();
        }

        private bool NominasExists(int id)
        {
            return (_context.Nominas?.Any(n => n.NominaId == id)).GetValueOrDefault();
        }
    }
}
